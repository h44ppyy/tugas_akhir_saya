package tabian.com.actionbar.api;

import tabian.com.actionbar.data.GameItems;
import tabian.com.actionbar.data.Heroes;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 参考：
 * https://dev.dota2.com/showthread.php?t=58317
 * https://wiki.teamfortress.com/wiki/WebAPI#Dota_2
 *
 * Created by Tnno Wu on 2018/03/16.
 */

public interface AppService {

    /**
     * Heroes
     *
     * e.g. https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v1?key=9DAEA5347C0613E87B22FC264BEF55E2&language=zh-CN
     */
    @GET("IEconDOTA2_570/GetHeroes/v1")
    Observable<Heroes> getHeroes(@Query("key") String key,
                                 @Query("language") String language);

    /**
     * Game items
     *
     * e.g. http://api.steampowered.com/IEconDOTA2_570/GetGameItems/v1?key=9DAEA5347C0613E87B22FC264BEF55E2&language=zh-CN
     */
    @GET("IEconDOTA2_570/GetGameItems/v1")
    Observable<GameItems> getGameItems(@Query("key") String key,
                                       @Query("language") String language);

    /**
     * League list
     *
     * e.g. http://api.steampowered.com/IDOTA2Match_570/GetLeagueListing/v1?key=9DAEA5347C0613E87B22FC264BEF55E2&language=zh-CN
     */


    /**
     * Live league games
     *
     * e.g. http://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v1?key=9DAEA5347C0613E87B22FC264BEF55E2
     */

    /**
     * Match details
     *
     * e.g. http://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/v1?key=9DAEA5347C0613E87B22FC264BEF55E2&match_id=2786897767
     */

    /**
     * Match history
     *
     * e.g. http://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/v1?key=9DAEA5347C0613E87B22FC264BEF55E2
     */

    /**
     * Match history by sequence num
     *
     * e.g. http://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v1?key=9DAEA5347C0613E87B22FC264BEF55E2
     */

    /**
     * Team info by team id
     *
     * e.g. http://api.steampowered.com/IDOTA2Match_570/GetTeamInfoByTeamID/v1?key=9DAEA5347C0613E87B22FC264BEF55E2
     */
}
