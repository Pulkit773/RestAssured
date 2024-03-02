package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationAPI.PlaylistAPI;
import com.spotify.oauth2.pojo.PlayList.Error;
import com.spotify.oauth2.pojo.PlayList.PlayList;
import com.spotify.oauth2.utils.DataLoader;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Auth 2.0")
@Feature("PlayList API")
public class PlaylistTests extends BaseTest{

    @Story("Create a PlayList API")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("123456")
    @TmsLink("12345")
    @Description("this is the description")
    @Test(description = "should be able to create a playlist")
    public void shouldBeAbleToCreateplaylist() {
        PlayList requestPlayList = playListBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.post(requestPlayList);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_201.getCode()));
        PlayList responsePlayList = response.as(PlayList.class);
        assertPlayListEqual(responsePlayList, requestPlayList);
    }

    @Test
    public void shouldBeAbleToGetPlaylist() {
        PlayList requestPlayList = playListBuilder("New Playlist 1", "New playlist description", false);
        Response response = PlaylistAPI.get(DataLoader.getInstance().getGetPlaylistId());
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
        PlayList responsePlayList = response.as(PlayList.class);
        assertPlayListEqual(responsePlayList, requestPlayList);
    }

    @Test
    public void shouldBeAbleToUpdatePlayLists() {
        PlayList requestPlayList = playListBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.update(DataLoader.getInstance().getUpdatePlaylistId(), requestPlayList);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_200.getCode()));
    }

    @Story("Create a PlayList API")
    @Test
    public void shouldNotBeAbleToCreateplaylistWithoutName() {
        PlayList requestPlayList = playListBuilder("", generateDescription(), false);
        Response response = PlaylistAPI.post(requestPlayList);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_400.getCode()));
        Error error = response.as(Error.class);
        assertError(error, 400, StatusCode.CODE_400.getMsg());
    }

    @Test
    public void shouldNotBeAbleToCreateplaylistWithExpireToken() {
        String invalidToken = "12345";
        PlayList requestPlayList = playListBuilder(generateName(), generateDescription(), false);
        Response response = PlaylistAPI.post(invalidToken, requestPlayList);
        assertThat(response.statusCode(), equalTo(StatusCode.CODE_401.getCode()));
        Error error = response.as(Error.class);
        assertError(error, StatusCode.CODE_401.getCode(), StatusCode.CODE_401.getMsg());
    }

    @Step
    public PlayList playListBuilder(String name, String description, boolean _public) {
        PlayList playList = new PlayList();
        playList.setName(name);
        playList.setDescription(description);
        playList.set_public(_public);
        return playList;
    }

    @Step
    public void assertPlayListEqual(PlayList responsePlayList, PlayList requestPlayList) {
        assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
    }

    @Step
    public void assertError(Error responseErr, int expectedStatus, String expectedErrorMsg) {
        assertThat(responseErr.getError().getStatus(), equalTo(expectedStatus));
        assertThat(responseErr.getError().getMessage(), equalTo(expectedErrorMsg));
    }
}

