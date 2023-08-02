package org.example.SiteService;

import org.example.Dto.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Trendyol {

    public List<Product> productList = new ArrayList<>();


    public void scrapeProductsTrendyol() throws IOException {
        final String url = "https://www.trendyol.com/laptop-x-c103108";


        final Document document = Jsoup.connect(url).timeout(10000).get();

        final Elements body = document.select("div.prdct-cntnr-wrppr");

        for (Element e : body.select("div.p-card-chldrn-cntnr.card-border")) {
            String link = e.select("a").attr("href");
            String img = e.select("img.p-card-img").attr("src");
            String name = e.select("span.prdct-desc-cntnr-name.hasRatings").text();
            String price = e.select("div.prc-box-dscntd").text();


            Product product = new Product(name, price, "https://www.trendyol.com/" + link);
            productList.add(product);


            System.out.println(name);
            System.out.println(price);
            System.out.println(img);
            System.out.println("https://www.trendyol.com/" + link);
            System.out.println();
        }

    }


}










