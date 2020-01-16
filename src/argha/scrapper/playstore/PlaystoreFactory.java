/*
 * The MIT License
 *
 * Copyright 2020 Argha Das.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package argha.scrapper.playstore;

import argha.scrapper.model.AdditionalModel;
import argha.scrapper.model.PlaystoreModel;
import argha.scrapper.utill.DocumentUtil;
import argha.scrapper.utill.JsonUtil;
import interfaces.MetaDataProgressListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Argha Das
 */
public class PlaystoreFactory {

    private String packageName;
    private MetaDataProgressListener listener;
    private Document document;
    List<PlaystoreModel> model;

    public PlaystoreFactory() {
        init();
    }

    public PlaystoreFactory(String packageName) {
        this.packageName = packageName;
        init();
    }
    
    private void init() {
        model = new ArrayList<>();
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setMetaDataProgressListener(MetaDataProgressListener listener) {
        this.listener = listener;
    }

    public void startExtractingData() {
        listener.onDownloadingMetaData();
        try {
            document = new DocumentUtil(packageName).getDocument();
            listener.onParsingMetaData();
            parseData();
            String json = new JsonUtil().getJsonFrom(model);
            if(!json.isBlank()) {
                listener.onMetaDataIsReady(json);
            }
        } catch (IOException ex) {
            listener.onErrorOccured("No Package Found");
        }
    }
    
    private void parseData() {
        String title = getTitle();
        String developer = getDeveloper();
        String category = getCategory();
        String icon = getIcon();
        String iconHD = getIconHD();
        List<String> screenshots = getScreenshots();
        List<String> screenshotsHD = getScreenshotsHD();
        String description = getDescription();
        List<AdditionalModel> additionalModel = getAdditionalInformation();
        String ratings = getRatings();
        model.clear();
        model.add(new PlaystoreModel(title, developer, category, icon, iconHD, screenshots, screenshotsHD, description, ratings, additionalModel));
    }

    //********PARSING CODE BELOW************//
    public String getTitle() {
        return document.select("h1.AHFaub > span").text();
    }

    public String getDeveloper() {
        return document.selectFirst("span.UAO9ie > a").text();
    }

    public String getCategory() {
        Elements elements = document.select("span.UAO9ie > a");
        for (Element element : elements) {
            if (element.hasAttr("itemprop")) {
                return element.text();
            }
        }
        return null;
    }

    public String getIcon() {
        return document.select("div.xSyT2c > img").attr("src");
    }

    public String getIconHD() {
        return document.select("div.xSyT2c > img").attr("srcset").replace(" 2x", "");
    }

    public List<String> getScreenshots() {
        List<String> screenshots = new ArrayList<>();
        screenshots.clear();
        Elements img = document.select("div.u3EI9e").select("button.Q4vdJd").select("img");
        for (Element src : img) {
            if (src.hasAttr("data-src")) {
                screenshots.add(src.attr("data-src"));
            } else {
                screenshots.add(src.attr("src"));
            }
        }
        return screenshots;
    }

    public List<String> getScreenshotsHD() {
        List<String> screenshots = new ArrayList<>();
        screenshots.clear();
        Elements img = document.select("div.u3EI9e").select("button.Q4vdJd").select("img");
        for (Element src : img) {
            if (src.hasAttr("data-src")) {
                screenshots.add(src.attr("data-srcset").replace(" 2x", ""));
            } else {
                screenshots.add(src.attr("srcset").replace(" 2x", ""));
            }
        }
        return screenshots;
    }

    public String getDescription() {
        return document.select("div.DWPxHb > span").text();
    }

    public List<AdditionalModel> getAdditionalInformation() {
        List<AdditionalModel> additionalModel = new ArrayList<>();
        additionalModel.clear();
        Elements additionDiv = document.select("div.IQ1z0d > div.IxB2fe > div.hAyfc");
        for (Element e : additionDiv) {
            Elements div = e.select("div.BgcNfc");
            String title = div.text();
            String value = e.select("span.htlgb > div.IQ1z0d > span.htlgb").text().replace("Learn More", "");
            additionalModel.add(new AdditionalModel(title, value));
        }
        return additionalModel;
    }

    public String getRatings() {
        return document.select("div.BHMmbe").text();
    }

    public void clearExtractor() {
        model.clear();
    }
}
