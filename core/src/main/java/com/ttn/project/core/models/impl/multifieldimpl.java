
package com.ttn.project.core.models.impl;



import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.ttn.project.core.models.MultiFieldmodel;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.List;

@Model(
        adaptables = Resource.class,
        adapters = MultiFieldmodel.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class multifieldimpl  implements MultiFieldmodel {

    @Self
    private Resource resource;

    @ValueMapValue
    private String[] pages;

    @Override
    public List<String> getPageTitles() {
        List<String> titles = new ArrayList<>();

        if (pages != null && pages.length > 0) {
            ResourceResolver resolver = resource.getResourceResolver();
            PageManager pageManager = resolver.adaptTo(PageManager.class);

            for (String path : pages) {
                Page page = pageManager != null ? pageManager.getPage(path) : null;
                if (page != null) {
                    titles.add(page.getTitle());
                }
            }
        }
        return titles;
    }
}

