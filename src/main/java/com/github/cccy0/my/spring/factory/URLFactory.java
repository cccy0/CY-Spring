package com.github.cccy0.my.spring.factory;

import com.github.cccy0.my.spring.ioc.UrlMethodRelate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhai
 * 2020/9/9 16:05
 */

public class URLFactory {
    private URLFactory() {
    }

    private final List<UrlMethodRelate> urlMethodRelates = new ArrayList<>();

    public UrlMethodRelate gainUrlMethodRelate(String url) {
        for (UrlMethodRelate u : urlMethodRelates) {
            if (u.getUrl().equals(url)) {
                return u;
            }
        }
        return null;
    }

    public boolean addUrlMethodRelate(UrlMethodRelate umr) {
        return urlMethodRelates.add(umr);
    }

    private static class Singleton {
        private static final URLFactory instance = new URLFactory();
    }

    public static URLFactory getInstance() {
        return URLFactory.Singleton.instance;
    }
}
