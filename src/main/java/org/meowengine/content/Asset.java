package org.meowengine.content;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Asset {

    public Asset(ContentManager contentManager, String location) {
        log.trace("Trying to load asset in " + location);
    }

    private Asset() {

    }
}
