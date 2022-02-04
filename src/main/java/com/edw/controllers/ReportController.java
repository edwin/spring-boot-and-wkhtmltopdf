package com.edw.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;

/**
 * <pre>
 *     com.edw.controllers.ReportController
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 04 Feb 2022 11:10
 */
@Controller
public class ReportController {

    @GetMapping(
            value = "/generate-report",
            produces = MediaType.APPLICATION_PDF_VALUE
    )
    public @ResponseBody byte[] generateReport(@RequestParam(value = "name") String name,
                                               @RequestParam(value = "address") String address) throws Exception {
        String uuid = UUID.randomUUID().toString();
        Path pathHtml = Paths.get("/tmp/" + uuid + ".html");
        Path pathPdf = Paths.get("/tmp/" + uuid + ".pdf");

        try {
            // read the template and fill the data
            String htmlContent = new Scanner(getClass().getClassLoader().getResourceAsStream("template.html"), "UTF-8")
                                    .useDelimiter("\\A")
                                    .next();
            htmlContent = htmlContent.replace("$name", name)
                                        .replace("$address", address);

            // write to html
            Files.write(pathHtml, htmlContent.getBytes());

            // convert html to pdf
            Process generateToPdf = Runtime.getRuntime().exec("wkhtmltopdf " + pathHtml.toString() + " " + pathPdf.toString() );
            generateToPdf.waitFor();

            // deliver pdf
            return Files.readAllBytes(pathPdf);

        } finally {
            // delete temp files
            Files.delete(pathHtml);
            Files.delete(pathPdf);
        }
    }
}
