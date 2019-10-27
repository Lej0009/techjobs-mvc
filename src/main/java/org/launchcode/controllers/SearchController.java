package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    // After looking up the search results via the JobData class, you'll need to
    // pass them into the search.html view via the model. You'll also need to pass
    // ListController.columnChoices to the view, as the existing search handler does

    @RequestMapping(value = "/search/results", method = RequestMethod.GET)
    public String search(@RequestParam HashMap searchType, @RequestParam String searchTerm) {

        // load data, if not already loaded
        loadData();

        ArrayList<HashMap<String, String>> someJobs = new ArrayList<>();

        for (HashMap<String, String> row : allJobs) {

            for (String key : row.keySet()) {
                String kValue = row.get(key);

                if (kValue.toLowerCase().contains(searchTerm.toLowerCase())) {
                    someJobs.add(row);
                }
            }

            for (String value : row.values()) {
                String vValue = row.get(value);

                if (vValue.toLowerCase().contains(searchTerm.toLowerCase())) {
                    someJobs.add(row);
                }
            }
        }
        model.addAttribute("columns", ListController.columnChoices);
        return someJobs;
    }
}

