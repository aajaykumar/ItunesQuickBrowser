package com.ajay.itunesquickbrowser.network;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by ajay on 1/13/16.
 */
public class SearchResponseReceivedEventTest {

    @Test
    public void testSearchResponseReceivedEventReturnsProperState() {
        final SearchResponseReceivedEvent event = new SearchResponseReceivedEvent(true);
        assertTrue(event.isSuccessful());
        assertFalse(event.isEmpty());

        final SearchResponseReceivedEvent event2 = new SearchResponseReceivedEvent(false);
        assertFalse(event2.isSuccessful());
        assertFalse(event2.isEmpty());

        final SearchResponseReceivedEvent event3 = new SearchResponseReceivedEvent(true, true);
        assertTrue(event3.isSuccessful());
        assertTrue(event3.isEmpty());
    }
}
