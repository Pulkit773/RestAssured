package com.spotify.oauth2.api.submit;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.PlayList.PlayList;
import com.spotify.oauth2.pojo.createLead.Lead;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

public class LeadAPI {

    @Step
    public static Response put(Lead requestCreateLead) {
        return RestResource.putLead("/growth/shop/v2/leads", requestCreateLead);
        //return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), requestPlayList);
    }

    public static Response post(String token, PlayList requestPlayList) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, token, requestPlayList);
    }

    public static Response get(String playListId) {
        return RestResource.get(PLAYLISTS + "/" + playListId, getToken());
    }

    public static Response update(String playListId, PlayList requestPlayList) {
        return RestResource.update(PLAYLISTS + "/" + playListId, getToken(), requestPlayList);
    }

}
