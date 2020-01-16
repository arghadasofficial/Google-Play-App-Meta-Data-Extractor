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
package argha.scrapper.utill;

import argha.scrapper.model.PlaystoreModel;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Argha Das
 */
public class JsonUtil {

    public String getJsonFrom(List<PlaystoreModel> model) {
        JSONObject root = new JSONObject();
        
        JSONObject meta_deta = new JSONObject();
        meta_deta.put("title", model.get(0).getTitle());
        meta_deta.put("developer", model.get(0).getDeveloper());
        meta_deta.put("category", model.get(0).getCategory());
        meta_deta.put("icon", model.get(0).getIcon());
        meta_deta.put("iconHD", model.get(0).getIconHD());
        meta_deta.put("description", model.get(0).getDescription());
        meta_deta.put("ratings", model.get(0).getRatings());

        JSONArray screenshots = new JSONArray();
        screenshots.put(model.get(0).getScreenshots());
        meta_deta.put("screenshots", screenshots);

        JSONArray screenshotsHD = new JSONArray();
        screenshotsHD.put(model.get(0).getScreenshotsHD());
        meta_deta.put("screenshotsHD", screenshotsHD);

        JSONArray additionalInfo = new JSONArray();
        for (int i = 0; i < model.get(0).getAdditionalInfo().size(); i++) {
            JSONObject object = new JSONObject();
            object.put("info_title", model.get(0).getAdditionalInfo().get(i).getTitle());
            object.put("info_value", model.get(0).getAdditionalInfo().get(i).getValue());
            additionalInfo.put(object);
        }
        meta_deta.put("additionalInfo", additionalInfo);

        root.put("meta_data", meta_deta);

        return root.toString();
    }
}
