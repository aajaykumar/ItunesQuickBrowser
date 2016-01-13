package com.ajay.itunesquickbrowser.model;

import com.google.gson.Gson;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * Created by ajay on 1/12/16.
 */
public class ParserLogicUnitTest {

    public static final String SAMPLE_JSON = "{ \"resultCount\":1, \"results\": [ {\"wrapperType\":\"track\", \"kind\":\"song\", \"artistId\":281070699, \"collectionId\":321595208, \"trackId\":321595573, \"artistName\":\"Florence + The Machine\", \"collectionName\":\"Lungs\", \"trackName\":\"Dog Days Are Over\", \"collectionCensoredName\":\"Lungs\", \"trackCensoredName\":\"Dog Days Are Over\", \"artistViewUrl\":\"https://itunes.apple.com/us/artist/florence-+-the-machine/id281070699?uo=4\", \"collectionViewUrl\":\"https://itunes.apple.com/us/album/dog-days-are-over/id321595208?i=321595573&uo=4\", \"trackViewUrl\":\"https://itunes.apple.com/us/album/dog-days-are-over/id321595208?i=321595573&uo=4\", \"previewUrl\":\"http://a1416.phobos.apple.com/us/r1000/067/Music/42/0f/38/mzm.cjhdrdvl.aac.p.m4a\", \"artworkUrl30\":\"http://is3.mzstatic.com/image/thumb/Music/v4/bf/49/3a/bf493a9d-9700-70a8-7d34-fe4f7151bb76/source/30x30bb.jpg\", \"artworkUrl60\":\"http://is3.mzstatic.com/image/thumb/Music/v4/bf/49/3a/bf493a9d-9700-70a8-7d34-fe4f7151bb76/source/60x60bb.jpg\", \"artworkUrl100\":\"http://is3.mzstatic.com/image/thumb/Music/v4/bf/49/3a/bf493a9d-9700-70a8-7d34-fe4f7151bb76/source/100x100bb.jpg\", \"collectionPrice\":8.99, \"trackPrice\":1.29, \"releaseDate\":\"2009-07-07T07:00:00Z\", \"collectionExplicitness\":\"notExplicit\", \"trackExplicitness\":\"notExplicit\", \"discCount\":1, \"discNumber\":1, \"trackCount\":13, \"trackNumber\":1, \"trackTimeMillis\":252795, \"country\":\"USA\", \"currency\":\"USD\", \"primaryGenreName\":\"Alternative\", \"radioStationUrl\":\"https://itunes.apple.com/station/idra.321595573\", \"isStreamable\":true}] }";

    private static final int SAMPLE_COUNT = 1;

    private static final String SAMPLE_TITLE = "Dog Days Are Over";

    private static final float SAMPLE_PRICE = 1.29f;

    private static final String SAMPLE_KIND = "song";

    @Test
    public void testJsonDeserialization() {
        final Gson gson = new Gson();

        final SearchResponse searchResponse = gson.fromJson(SAMPLE_JSON, SearchResponse.class);

        assertNotNull(searchResponse);

        assertEquals(SAMPLE_COUNT, searchResponse.resultCount);

        final Result result = searchResponse.getResult(0);

        assertNotNull(result);

        assertNull(searchResponse.getResult(1));

        assertEquals(SAMPLE_TITLE, result.trackName);

        assertEquals(SAMPLE_PRICE, result.trackPrice);

        assertEquals(SAMPLE_KIND, result.kind);
    }
}
