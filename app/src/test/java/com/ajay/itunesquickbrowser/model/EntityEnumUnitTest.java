package com.ajay.itunesquickbrowser.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by ajay on 1/12/16.
 */
public class EntityEnumUnitTest {

    @Test
    public void testDisplayNameAndValueAreUnique() {

        final Entity[] entities = Entity.values();

        for (final Entity entity : entities) {
            for (final Entity entity2 : entities) {
                if (entity.equals(entity2)) {
                    assertEquals(entity.getDisplayName(), entity2.getDisplayName());
                    assertEquals(entity.getValue(), entity2.getValue());
                } else {
                    assertNotEquals(entity.getDisplayName(), entity2.getDisplayName());
                    assertNotEquals(entity.getValue(), entity2.getValue());
                }
            }
        }

    }

    @Test
    public void testValueOf() {
        assertEquals(Entity.ALL, Entity.valueOf("ALL"));
        assertEquals(Entity.MOVIE, Entity.valueOf("MOVIE"));
        assertEquals(Entity.PODCAST, Entity.valueOf("PODCAST"));
        assertEquals(Entity.MUSIC, Entity.valueOf("MUSIC"));
        assertEquals(Entity.MUSIC_VIDEO, Entity.valueOf("MUSIC_VIDEO"));
        assertEquals(Entity.AUDIO_BOOK, Entity.valueOf("AUDIO_BOOK"));
        assertEquals(Entity.SHORT_FILM, Entity.valueOf("SHORT_FILM"));
        assertEquals(Entity.TV_SHOW, Entity.valueOf("TV_SHOW"));
        assertEquals(Entity.SOFTWARE, Entity.valueOf("SOFTWARE"));
        assertEquals(Entity.EBOOK, Entity.valueOf("EBOOK"));

        assertNotEquals(Entity.EBOOK, Entity.valueOf("ALL"));
        assertNotEquals(Entity.ALL, Entity.valueOf("EBOOK"));
    }
}
