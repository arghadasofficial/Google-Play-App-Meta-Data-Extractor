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
package argha.scrapper.model;

import java.util.List;

/**
 *
 * @author Argha Das
 */
public class PlaystoreModel {

    private String title;
    private String developer;
    private String category;
    private String icon;
    private String iconHD;
    private List<String> screenshots;
    private List<String> screenshotsHD;
    private String description;
    private String ratings;
    private List<AdditionalModel> additionalInfo;

    public PlaystoreModel(String title, String developer, String category, String icon, String iconHD, List<String> screenshots, List<String> screenshotsHD, String description, String ratings, List<AdditionalModel> additionalInfo) {
        this.title = title;
        this.developer = developer;
        this.category = category;
        this.icon = icon;
        this.iconHD = iconHD;
        this.screenshots = screenshots;
        this.screenshotsHD = screenshotsHD;
        this.description = description;
        this.ratings = ratings;
        this.additionalInfo = additionalInfo;
    }

    public PlaystoreModel() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconHD() {
        return iconHD;
    }

    public void setIconHD(String iconHD) {
        this.iconHD = iconHD;
    }

    public List<String> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<String> screenshots) {
        this.screenshots = screenshots;
    }

    public List<String> getScreenshotsHD() {
        return screenshotsHD;
    }

    public void setScreenshotsHD(List<String> screenshotsHD) {
        this.screenshotsHD = screenshotsHD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public List<AdditionalModel> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(List<AdditionalModel> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
