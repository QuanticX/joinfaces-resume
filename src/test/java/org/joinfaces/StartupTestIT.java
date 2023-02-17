package org.joinfaces;

import org.junit.jupiter.api.Test;

public class StartupTestIT extends AbstractLoader{

    @Test
    public void testStartup(){
        webDriver.get("http://localhost:8080");
    }



}
