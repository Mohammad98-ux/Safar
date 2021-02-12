package Utils;

import com.example.android.safar.MoreInfo.AttractionMoreInfo;
import com.example.android.safar.MoreInfo.CountryMoreInfo;
import com.example.android.safar.R;
import com.example.android.safar.model.Attraction;
import com.example.android.safar.model.Coordinates;
import com.example.android.safar.model.Country;
import com.example.android.safar.model.CountryCulture;
import com.example.android.safar.model.Dish;

import java.util.ArrayList;

public class QueryUtils {

    // List of currently supported countries in the app.
    private static final String[] supportedCountries = {
            "Jordan", "Japan", "Canada", "Norway", "Chile", "UAE", "Germany"
    };

    public static ArrayList<Country> getSupportedCountries() {
        ArrayList<Country> supportedCountriesList = new ArrayList<>();

        for (String countryName : supportedCountries) {
            supportedCountriesList.add(getCountryByName(countryName));
        }

        return supportedCountriesList;
    }

    public static Country getCountryByName(String countryName) {
        switch (countryName) {
            case "Jordan":
                return new Country(
                        "Jordan, Asia",
                        R.drawable.jordan_flag,
                        R.drawable.jordan_overview_petra,
                        getMoreInfoOf("Jordan"),
                        getCultureOf("Jordan"),
                        getDishesListOf("Jordan"),
                        "https://www.mota.gov.jo/default.aspx",
                        new String[]{"Petra", "Wadi Rum", "Aqaba", "Roman Amphitheater", "Mount Nebo"});

            case "Japan":
                return new Country("Japan, Asia",
                        R.drawable.japan_flag,
                        R.drawable.japan_overview_image,
                        getMoreInfoOf("Japan"),
                        getCultureOf("Japan"),
                        getDishesListOf("Japan"),
                        "https://www.mlit.go.jp/kankocho/en/",
                        new String[]{"Mount Fuji", "Tokyo Imperial Palace", "Itsukushima Shrine", "Arashiyama", "Osaka Castle"});

            case "Canada":
                return new Country("Canada, North America",
                        R.drawable.canada_flag,
                        R.drawable.canada_overview_image,
                        getMoreInfoOf("Canada"),
                        getCultureOf("Canada"),
                        getDishesListOf("Canada"),
                        "https://www.ic.gc.ca/eic/site/134.nsf/eng/home",
                        new String[]{"Bay of Fundy", "Vieux Quebec", "Stanley Park", "Butchart Gardens", "Banff National Park"});

            case "Norway":
                return new Country("Norway, Europe",
                        R.drawable.norway_flag,
                        R.drawable.norway_overview_image,
                        getMoreInfoOf("Norway"),
                        getCultureOf("Norway"),
                        getDishesListOf("Norway"),
                        "https://www.visitnorway.com/",
                        new String[]{"Bryggen", "Preikestolen", "Lofoten Islands", "Geiramger Fjord", "Akershus Fortress"});

            case "Chile":
                return new Country("Chile, South America",
                        R.drawable.chile_flag,
                        R.drawable.chile_overview_image,
                        getMoreInfoOf("Chile"),
                        getCultureOf("Chile"),
                        getDishesListOf("Chile"),
                        "https://www.economia.gob.cl/",
                        new String[]{"Anakena", "El-Tatio", "Cape Horn", "Villarrica Volcano", "Valley of The Moon"});

            case "UAE":
                return new Country("UAE, Asia",
                        R.drawable.uae_flag,
                        R.drawable.uae_overview_image,
                        getMoreInfoOf("UAE"),
                        getCultureOf("UAE"),
                        getDishesListOf("UAE"),
                        "https://www.dubaitourism.gov.ae/en/",
                        new String[]{"Burj Khalifa", "Dubai Aquarium", "Palm Jumeirah", "Dubai Frame", "Burj Al-Arab"}
                );

            case "Germany":
                return new Country(
                        "Germany, Europe",
                        R.drawable.germany_flag,
                        R.drawable.germany_overview_image,
                        getMoreInfoOf("Germany"),
                        getCultureOf("Germany"),
                        getDishesListOf("Germany"),
                        "https://www.germany.travel/en/home.html",
                        new String[]{"Brandenburg Gate", "The Rhine Valley", "Neuschwanstein Castle", "Museum Island", "Rothenburg ob der Tauber"}
                );

            default:
                throw new IllegalStateException("Unknown country " + countryName);
        }
    }

    public static Attraction getAttractionByName(String attrName) {
        switch (attrName) {
            case "Petra":
                return new Attraction(
                        "Petra",
                        R.drawable.jordan_overview_petra,
                        new AttractionMoreInfo(
                                R.string.petra_more_info,
                                new int[]{
                                        R.drawable.more_petra_1,
                                        R.drawable.more_petra_2,
                                        R.drawable.more_petra_3,
                                        R.drawable.more_petra_4
                                }
                        ),
                        new Coordinates(30.328509610594647, 35.44436117305987));
            case "Wadi Rum":
                return new Attraction(
                        "Wadi Rum",
                        R.drawable.jordan_wadi_rum,
                        new AttractionMoreInfo(
                                R.string.wadi_rum_more_info,
                                new int[]{
                                        R.drawable.more_wadi_rum_1,
                                        R.drawable.more_wadi_rum_2,
                                        R.drawable.more_wadi_rum_3,
                                        R.drawable.more_wadi_rum_4
                                }
                        ),
                        new Coordinates(29.574794062293165, 35.421020113635855));
            case "Aqaba":
                return new Attraction(
                        "Aqaba",
                        R.drawable.jordan_aqaba,
                        new AttractionMoreInfo(
                                R.string.aqaba_more_info,
                                new int[]{
                                        R.drawable.more_aqaba_1,
                                        R.drawable.more_aqaba_2,
                                        R.drawable.more_aqaba_3,
                                        R.drawable.more_aqaba_4
                                }
                        ),
                        new Coordinates(29.554696500711124, 34.98794505811711));
            case "Roman Amphitheater":
                return new Attraction(
                        "Roman Amphitheater",
                        R.drawable.jordan_roman_amphitheater,
                        new AttractionMoreInfo(
                                R.string.roman_theater_more_info,
                                new int[]{
                                        R.drawable.more_roman_theater_1,
                                        R.drawable.more_roman_theater_2,
                                        R.drawable.more_roman_theater_3,
                                        R.drawable.more_roman_theater_4
                                }
                        ),
                        new Coordinates(31.951622332912272, 35.9393031735368));
            case "Mount Nebo":
                return new Attraction(
                        "Mount Nebo",
                        R.drawable.jordan_mountain_nebo,
                        new AttractionMoreInfo(
                                R.string.nebo_more_info,
                                new int[]{
                                        R.drawable.more_nebo_1,
                                        R.drawable.more_nebo_2,
                                        R.drawable.more_nebo_3,
                                        R.drawable.more_nebo_4
                                }
                        ),
                        new Coordinates(31.768346029980272, 35.72527429437531));
            case "Mount Fuji":
                return new Attraction(
                        "Mount Fuji",
                        R.drawable.japan_mount_fuji,
                        new AttractionMoreInfo(
                                R.string.fuji_more_info,
                                new int[]{
                                        R.drawable.more_fuji_1,
                                        R.drawable.more_fuji_2,
                                        R.drawable.more_fuji_3,
                                        R.drawable.more_fuji_4
                                }
                        ),
                        new Coordinates(35.362828616830214, 138.72727771043728));
            case "Tokyo Imperial Palace":
                return new Attraction(
                        "Tokyo Imperial Palace",
                        R.drawable.japan_imperial_palace,
                        new AttractionMoreInfo(
                                R.string.imperial_more_info,
                                new int[]{
                                        R.drawable.more_imperial_1,
                                        R.drawable.more_imperial_2,
                                        R.drawable.more_imperial_3,
                                        R.drawable.more_imperial_4
                                }
                        ),
                        new Coordinates(35.685704604108224, 139.75278984835717));
            case "Itsukushima Shrine":
                return new Attraction(
                        "Itsukushima Shrine",
                        R.drawable.japan_itsukushima_shrine,
                        new AttractionMoreInfo(
                                R.string.shrine_more_info,
                                new int[]{
                                        R.drawable.more_shrine_1,
                                        R.drawable.more_shrine_2,
                                        R.drawable.more_shrine_3,
                                        R.drawable.more_shrine_4
                                }
                        ),
                        new Coordinates(34.29623312633173, 132.31979641580506));
            case "Arashiyama":
                return new Attraction(
                        "Arashiyama",
                        R.drawable.japan_arashiyama,
                        new AttractionMoreInfo(
                                R.string.arashiyama_more_info,
                                new int[]{
                                        R.drawable.more_arashiyama_1,
                                        R.drawable.more_arashiyama_2,
                                        R.drawable.more_arashiyama_3,
                                        R.drawable.more_arashiyama_4
                                }
                        ),
                        new Coordinates(35.00953687528118, 135.66681591570168));
            case "Osaka Castle":
                return new Attraction(
                        "Osaka Castle",
                        R.drawable.japan_osaka_castle,
                        new AttractionMoreInfo(
                                R.string.osaka_castle_more_info,
                                new int[]{
                                        R.drawable.more_osaka_castle_1,
                                        R.drawable.more_osaka_castle_2,
                                        R.drawable.more_osaka_castle_3,
                                        R.drawable.more_osaka_castle_4
                                }
                        ),
                        new Coordinates(34.687330523563276, 135.52595416119735));
            case "Bay of Fundy":
                return new Attraction(
                        "Bay of Fundy",
                        R.drawable.canada_bay_of_fundy,
                        new AttractionMoreInfo(
                                R.string.bay_of_fundy_more_info,
                                new int[]{
                                        R.drawable.more_fundy_1,
                                        R.drawable.more_fundy_2,
                                        R.drawable.more_fundy_3,
                                        R.drawable.more_fundy_4
                                }
                        ),
                        new Coordinates(44.81145144693026, -66.983501148373));
            case "Vieux Quebec":
                return new Attraction(
                        "Vieux Quebec",
                        R.drawable.canada_vieux_quebec,
                        new AttractionMoreInfo(
                                R.string.quebec_more_info,
                                new int[]{
                                        R.drawable.more_quebec_1,
                                        R.drawable.more_quebec_2,
                                        R.drawable.more_quebec_3,
                                        R.drawable.more_quebec_4
                                }
                        ),
                        new Coordinates(46.812059052003356, -71.20640105643913));
            case "Stanley Park":
                return new Attraction(
                        "Stanley Park",
                        R.drawable.canada_stanley_park,
                        new AttractionMoreInfo(
                                R.string.stanley_park_more_info,
                                new int[]{
                                        R.drawable.more_stanley_park_1,
                                        R.drawable.more_stanley_park_2,
                                        R.drawable.more_stanley_park_3,
                                        R.drawable.more_stanley_park_4
                                }
                        ),
                        new Coordinates(49.304281115213755, -123.14430852457566));
            case "Butchart Gardens":
                return new Attraction(
                        "Butchart Gardens",
                        R.drawable.canada_butchart_gardens,
                        new AttractionMoreInfo(
                                R.string.butchart_more_info,
                                new int[]{
                                        R.drawable.more_butchart_1,
                                        R.drawable.more_butchart_2,
                                        R.drawable.more_butchart_3,
                                        R.drawable.more_butchart_4
                                }
                        ),
                        new Coordinates(48.56357825431533, -123.47061010488355));
            case "Banff National Park":
                return new Attraction(
                        "Banff National Park",
                        R.drawable.canada_banff_national_park,
                        new AttractionMoreInfo(
                                R.string.banff_national_park_more_info,
                                new int[]{
                                        R.drawable.more_banff_1,
                                        R.drawable.more_banff_2,
                                        R.drawable.more_banff_3,
                                        R.drawable.more_banff_4
                                }
                        ),
                        new Coordinates(51.171238032026054, -115.57099680573535));
            case "Bryggen":
                return new Attraction(
                        "Bryggen",
                        R.drawable.norway_bryggen,
                        new AttractionMoreInfo(
                                R.string.bryggen_more_info,
                                new int[]{
                                        R.drawable.more_bryggen_1,
                                        R.drawable.more_bryggen_2,
                                        R.drawable.more_bryggen_3,
                                        R.drawable.more_bryggen_4
                                }
                        ),
                        new Coordinates(60.39768511703537, 5.324608473450496));
            case "Preikestolen":
                return new Attraction(
                        "Preikestolen",
                        R.drawable.norway_preikestolen,
                        new AttractionMoreInfo(
                                R.string.preikestolen_more_info,
                                new int[]{
                                        R.drawable.more_preikestolen_1,
                                        R.drawable.more_preikestolen_2,
                                        R.drawable.more_preikestolen_3,
                                        R.drawable.more_preikestolen_4
                                }
                        ),
                        new Coordinates(58.9864212763762, 6.190436213019485));
            case "Lofoten Islands":
                return new Attraction(
                        "Lofoten Islands",
                        R.drawable.norway_lofoten_islands,
                        new AttractionMoreInfo(
                                R.string.lofoten_more_info,
                                new int[]{
                                        R.drawable.more_lofoten_1,
                                        R.drawable.more_lofoten_2,
                                        R.drawable.more_lofoten_3,
                                        R.drawable.more_lofoten_4
                                }
                        ),
                        new Coordinates(68.49061399988858, 13.865585268990987));
            case "Geiramger Fjord":
                return new Attraction(
                        "Geiramger Fjord",
                        R.drawable.norway_geiranger_fjord,
                        new AttractionMoreInfo(
                                R.string.geiranger_fjord_more_info,
                                new int[]{
                                        R.drawable.more_fjord_1,
                                        R.drawable.more_fjord_2,
                                        R.drawable.more_fjord_3,
                                        R.drawable.more_fjord_4
                                }
                        ),
                        new Coordinates(62.10847205851619, 7.092384260661961));
            case "Akershus Fortress":
                return new Attraction(
                        "Akershus Fortress",
                        R.drawable.norway_akershus_fortress,
                        new AttractionMoreInfo(
                                R.string.akershus_fortress_more_info,
                                new int[]{
                                        R.drawable.more_akershus_1,
                                        R.drawable.more_akershus_2,
                                        R.drawable.more_akershus_3,
                                        R.drawable.more_akershus_4
                                }
                        ),
                        new Coordinates(59.90771777683915, 10.73713774561604));
            case "Anakena":
                return new Attraction(
                        "Anakena",
                        R.drawable.chile_anakena,
                        new AttractionMoreInfo(
                                R.string.anakena_more_info,
                                new int[]{
                                        R.drawable.more_anakena_1,
                                        R.drawable.more_anakena_2,
                                        R.drawable.more_anakena_3,
                                        R.drawable.more_anakena_4
                                }
                        ),
                        new Coordinates(-27.07320559162768, -109.32325998373983));
            case "El-Tatio":
                return new Attraction(
                        "El-Tatio",
                        R.drawable.chile_el_tatio,
                        new AttractionMoreInfo(
                                R.string.el_tatio_more_info,
                                new int[]{
                                        R.drawable.more_el_tatio_1,
                                        R.drawable.more_el_tatio_2,
                                        R.drawable.more_el_tatio_3,
                                        R.drawable.more_el_tatio_4
                                }
                        ),
                        new Coordinates(-22.334636820322807, -68.01300838392851));
            case "Cape Horn":
                return new Attraction(
                        "Cape Horn",
                        R.drawable.chile_cape_horn,
                        new AttractionMoreInfo(
                                R.string.cape_horn_more_info,
                                new int[]{
                                        R.drawable.more_cape_horn_1,
                                        R.drawable.more_cape_horn_2,
                                        R.drawable.more_cape_horn_3,
                                        R.drawable.more_cape_horn_4
                                }
                        ),
                        new Coordinates(-55.982010780064364, -67.26709585668094));
            case "Villarrica Volcano":
                return new Attraction(
                        "Villarrica Volcano",
                        R.drawable.chile_villarrica_volcano,
                        new AttractionMoreInfo(
                                R.string.villarrica_more_info,
                                new int[]{
                                        R.drawable.more_villarrica_1,
                                        R.drawable.more_villarrica_2,
                                        R.drawable.more_villarrica_3,
                                        R.drawable.more_villarrica_4
                                }
                        ),
                        new Coordinates(-39.28234681098377, -72.23128315512228));
            case "Valley of The Moon":
                return new Attraction(
                        "Valley of The Moon",
                        R.drawable.chile_valley_of_the_moon,
                        new AttractionMoreInfo(
                                R.string.valley_of_the_moon_more_info,
                                new int[]{
                                        R.drawable.more_valley_of_the_moon_1,
                                        R.drawable.more_valley_of_the_moon_2,
                                        R.drawable.more_valley_of_the_moon_3,
                                        R.drawable.more_valley_of_the_moon_4
                                }
                        ),
                        new Coordinates(-22.922953234979722, -68.2877440335005));

            case "Burj Khalifa":
                return new Attraction(
                        "Burj Khalifa",
                        R.drawable.uae_burj_khalifa,
                        new AttractionMoreInfo(
                                R.string.burj_khalifa_more_info,
                                new int[]{
                                        R.drawable.more_burj_khalifa_1,
                                        R.drawable.more_burj_khalifa_2,
                                        R.drawable.more_burj_khalifa_3,
                                        R.drawable.more_burj_khalifa_4
                                }
                        ),
                        new Coordinates(25.197208525788703, 55.27437686244877)
                );

            case "Dubai Aquarium":
                return new Attraction(
                        "Dubai Aquarium",
                        R.drawable.uae_dubaia_quarium,
                        new AttractionMoreInfo(
                                R.string.dubai_aquarium_more_info,
                                new int[]{
                                        R.drawable.more_dubai_aquarium_1,
                                        R.drawable.more_dubai_aquarium_2,
                                        R.drawable.more_dubai_aquarium_3,
                                        R.drawable.more_dubai_aquarium_4
                                }
                        ),
                        new Coordinates(25.19752821527333, 55.27849706332618)
                );

            case "Palm Jumeirah":
                return new Attraction(
                        "Palm Jumeirah",
                        R.drawable.uae_palm_jumeirah,
                        new AttractionMoreInfo(
                                R.string.palm_jumeirah_more_info,
                                new int[]{
                                        R.drawable.more_palm_jumeirah_1,
                                        R.drawable.more_palm_jumeirah_2,
                                        R.drawable.more_palm_jumeirah_3,
                                        R.drawable.more_palm_jumeirah_4
                                }
                        ),
                        new Coordinates(25.112458324059812, 55.138977471012154)
                );

            case "Dubai Frame":
                return new Attraction(
                        "Dubai Frame",
                        R.drawable.uae_dubai_frame,
                        new AttractionMoreInfo(
                                R.string.dubai_frame_more_info,
                                new int[]{
                                        R.drawable.more_dubai_frame_1,
                                        R.drawable.more_dubai_frame_2,
                                        R.drawable.more_dubai_frame_3,
                                        R.drawable.more_dubai_frame_4
                                }
                        ),
                        new Coordinates(25.235569876741696, 55.30036175857589)
                );

            case "Burj Al-Arab":
                return new Attraction(
                        "Burj Al-Arab",
                        R.drawable.uae_burj_al_arab,
                        new AttractionMoreInfo(
                                R.string.burj_al_arab_more_info,
                                new int[]{
                                        R.drawable.more_burj_al_arab_1,
                                        R.drawable.more_burj_al_arab_2,
                                        R.drawable.more_burj_al_arab_3,
                                        R.drawable.more_burj_al_arab_4
                                }
                        ),
                        new Coordinates(25.14121005545085, 55.185235792944674)
                );

            case "Brandenburg Gate":
                return new Attraction(
                        "Brandenburg Gate",
                        R.drawable.germany_brandenburggate,
                        new AttractionMoreInfo(
                                R.string.brandenburg_gate_more_info,
                                new int[]{
                                        R.drawable.more_brandenburg_gate_1,
                                        R.drawable.more_brandenburg_gate_2,
                                        R.drawable.more_brandenburg_gate_3,
                                        R.drawable.more_brandenburg_gate_4
                                }
                        ),
                        new Coordinates(52.516290326299874, 13.377699794220561)
                );

            case "The Rhine Valley":
                return new Attraction(
                        "The Rhine Valley",
                        R.drawable.germany_the_rhine_valley,
                        new AttractionMoreInfo(
                                R.string.the_rhine_valley_more_info,
                                new int[]{
                                        R.drawable.more_the_rhine_valley_1,
                                        R.drawable.more_the_rhine_valley_2,
                                        R.drawable.more_the_rhine_valley_3,
                                        R.drawable.more_the_rhine_valley_4
                                }
                        ),
                        new Coordinates(50.16146689553161, 7.700865402761768)
                );

            case "Neuschwanstein Castle":
                return new Attraction(
                        "Neuschwanstein Castle",
                        R.drawable.germany_neuschwanstein_castle,
                        new AttractionMoreInfo(
                                R.string.neuschwanstein_castle_more_info,
                                new int[]{
                                        R.drawable.more_neuschwanstein_castle_1,
                                        R.drawable.more_neuschwanstein_castle_2,
                                        R.drawable.more_neuschwanstein_castle_3,
                                        R.drawable.more_neuschwanstein_castle_4
                                }),
                        new Coordinates(47.55762606569619, 10.749812306326227)
                );

            case "Museum Island":
                return new Attraction(
                        "Museum Island",
                        R.drawable.germany_museum_island,
                        new AttractionMoreInfo(
                                R.string.museum_island_more_info,
                                new int[]{
                                        R.drawable.more_museum_island_1,
                                        R.drawable.more_museum_island_2,
                                        R.drawable.more_museum_island_3,
                                        R.drawable.more_museum_island_4
                                }),
                        new Coordinates(52.51717729714225, 13.401908082815169)
                );

            case "Rothenburg ob der Tauber":
                return new Attraction(
                        "Rothenburg ob der Tauber",
                        R.drawable.germany_rothenburg_ob_der_tauber,
                        new AttractionMoreInfo(
                                R.string.rothenburg_ob_der_tauber_more_info,
                                new int[]{
                                        R.drawable.more_rothenburg_ob_der_tauber_1,
                                        R.drawable.more_rothenburg_ob_der_tauber_2,
                                        R.drawable.more_rothenburg_ob_der_tauber_3,
                                        R.drawable.more_rothenburg_ob_der_tauber_4
                                }),
                        new Coordinates(49.38091907615942, 10.186693713658105)
                );

            default:
                throw new IllegalStateException("Unknown attraction " + attrName);
        }

    }

    public static CountryMoreInfo getMoreInfoOf(String countryName) {
        switch (countryName) {
            case "Jordan":
                return new CountryMoreInfo(
                        "Asia, Middle East",
                        9.956d,
                        89.342f,
                        "Amman",
                        "Syria (North)\nIraq (East)\nSaudi Arabia (South)\nPalestine (West)",
                        "Jordanian Dinar (JOD)",
                        R.string.jordan_quick_history
                );

            case "Japan":
                return new CountryMoreInfo(
                        "Asia, Far East",
                        126.5d,
                        377.915f,
                        "Tokyo",
                        "Japan doesn't have any land borders with any country",
                        "Japanese Yen (JPY)",
                        R.string.japan_quick_history
                );

            case "Canada":
                return new CountryMoreInfo(
                        "North America",
                        37.59d,
                        9.985000f,
                        "Ottawa",
                        "Alaska (West)\nUSA (South)",
                        "Canadian Dollar (CAD)",
                        R.string.canada_quick_history
                );

            case "Norway":
                return new CountryMoreInfo(
                        "Northern Europe",
                        5.433d,
                        385.207f,
                        "Oslo",
                        "Norway has land borders only to the east -- with Sweden, Finland & Russia",
                        "Norwegian krone (NOK)",
                        R.string.norway_quick_history
                );

            case "Chile":
                return new CountryMoreInfo(
                        "Eastern South America",
                        18.73d,
                        756.950f,
                        "Santiago",
                        "Argentina (East)\nBolivia (North)\nPeru (North)",
                        "Chilean Peso (CLP)",
                        R.string.chile_quick_history
                );

            case "UAE":
                return new CountryMoreInfo(
                        "Asia, Middle East",
                        9.631d,
                        67.340f,
                        "Abu Dhabi",
                        "Oman (North)\nOman (East)\nSaudi Arabia (South)\nSaudi Arabia (West)",
                        "UAE Dirham (AED)",
                        R.string.uae_quick_history
                );

            case "Germany":
                return new CountryMoreInfo(
                        "Western and Central Europe",
                        83.02d,
                        357.386f,
                        "Berlin",
                        "North Sea, The Baltic Sea, Denmark (North)\nNetherlands, Belgium, France (West)\nSwitzerland, Austria, Czech, Poland (South)",
                        "Euro (EUR)",
                        R.string.germany_quick_history
                );

            default:
                throw new IllegalStateException("Unknown Country " + countryName);
        }
    }

    public static CountryCulture getCultureOf(String countryName) {
        switch (countryName) {
            case "Jordan":
                return new CountryCulture(
                        R.drawable.jordan_culture,
                        R.string.jordan_culture_1,
                        R.string.jordan_culture_2);

            case "Japan":
                return new CountryCulture(
                        R.drawable.japan_culture,
                        R.string.japan_culture_1,
                        R.string.japan_culture_2);

            case "Canada":
                return new CountryCulture(
                        R.drawable.canada_culture,
                        R.string.canada_culture_1,
                        R.string.canada_culture_2);

            case "Norway":
                return new CountryCulture(
                        R.drawable.norway_culture,
                        R.string.norway_culture_1,
                        R.string.norway_culture_2);

            case "Chile":
                return new CountryCulture(
                        R.drawable.chile_culture,
                        R.string.chile_culture_1,
                        R.string.chile_culture_2);

            case "UAE":
                return new CountryCulture(
                        R.drawable.uae_culture,
                        R.string.uae_culture_1,
                        R.string.uae_culture_2);

            case "Germany":
                return new CountryCulture(
                        R.drawable.germany_culture,
                        R.string.germany_culture_1,
                        R.string.germany_culture_2);

            default:
                throw new IllegalStateException("Error in getCultureOf(String). Country not supported: " + countryName);
        }
    }

    public static ArrayList<Dish> getDishesListOf(String countryName) {
        ArrayList<Dish> dishesList = new ArrayList<>();
        switch (countryName) {
            case "Jordan":
                dishesList.add(new Dish("Mansaf", R.drawable.jordan_famous_dish_mansaf));
                dishesList.add(new Dish("Maqluba", R.drawable.jordan_famous_dish_maqluba));
                dishesList.add(new Dish("Flafel", R.drawable.jordan_famous_dish_flafel));
                dishesList.add(new Dish("Arabic Coffee", R.drawable.jordan_famous_dish_coffee));
                dishesList.add(new Dish("Kenafeh", R.drawable.jordan_famous_dish_kenafah));
                dishesList.add(new Dish("Shawarma", R.drawable.jordan_famous_dish_shawarma));
                break;

            case "Japan":
                dishesList.add(new Dish("Sushi", R.drawable.japan_famous_dish_sushi));
                dishesList.add(new Dish("Ramen", R.drawable.japan_famous_dish_ramen));
                dishesList.add(new Dish("Tempura", R.drawable.japan_famous_dish_tempura));
                dishesList.add(new Dish("Okonomiyaki", R.drawable.japan_famous_dish_okonomiyaki));
                dishesList.add(new Dish("Shabu Shabu", R.drawable.japan_famous_dish_shabu_shabu));
                dishesList.add(new Dish("Yakitori", R.drawable.japan_famous_dish_yakitori));
                break;

            case "Canada":
                dishesList.add(new Dish("Poutine", R.drawable.canada_famous_dish_poutine));
                dishesList.add(new Dish("Bannock", R.drawable.canada_famous_dish_bannock));
                dishesList.add(new Dish("Beavertails", R.drawable.canada_famous_dish_beavertails));
                dishesList.add(new Dish("Butter Tarts", R.drawable.canada_famous_dish_butter_tarts));
                dishesList.add(new Dish("Nanaimo Bars", R.drawable.canada_famous_dish_nanaimo_bars));
                dishesList.add(new Dish("Caesar Cocktail", R.drawable.canada_famous_dish_caesar_cocktail));
                break;

            case "Norway":
                dishesList.add(new Dish("Sursild", R.drawable.norway_famous_dish_sursild));
                dishesList.add(new Dish("Finnbiff", R.drawable.norway_famous_dish_finnbiff));
                dishesList.add(new Dish("Brunost", R.drawable.norway_famous_dish_brunost));
                dishesList.add(new Dish("Smoked Salmon", R.drawable.norway_famous_dish_smoked_salmon));
                dishesList.add(new Dish("Sodd", R.drawable.norway_famous_dish_sodd));
                dishesList.add(new Dish("Lutefisk", R.drawable.norway_famous_dish_lutefisk));
                break;

            case "Chile":
                dishesList.add(new Dish("Ajiaco", R.drawable.chile_famous_dish_ajiaco));
                dishesList.add(new Dish("Arrollado Huaso", R.drawable.chile_famous_dish_arrollado_huaso));
                dishesList.add(new Dish("Carbonada", R.drawable.chile_famous_dish_carbonada));
                dishesList.add(new Dish("Chapalele", R.drawable.chile_famous_dish_chapalele));
                dishesList.add(new Dish("Chorillana", R.drawable.chile_famous_dish_chorillana));
                dishesList.add(new Dish("Gambas al Pil Pil", R.drawable.chile_famous_dish_gambas_al_pil_pil));
                break;

            case "UAE":
                dishesList.add(new Dish("Khuzi", R.drawable.uae_famous_dish_khuzi));
                dishesList.add(new Dish("Tabbouleh", R.drawable.uae_famous_dish_tabbouleh));
                dishesList.add(new Dish("Machboos", R.drawable.uae_famous_dish_machboos));
                dishesList.add(new Dish("Biryani", R.drawable.uae_famous_dish_biryani));
                dishesList.add(new Dish("Stuffed Camel", R.drawable.uae_famous_dish_stuffed_camel));
                dishesList.add(new Dish("Shawarma", R.drawable.uae_famous_dish_shawarma));
                break;

            case "Germany":
                dishesList.add(new Dish("Sauerbraten", R.drawable.germany_famous_dish_sauerbraten));
                dishesList.add(new Dish("Schweinshaxe", R.drawable.germany_famous_dish_schweinshaxe));
                dishesList.add(new Dish("Rinderroulade", R.drawable.germany_famous_dish_rinderroulade));
                dishesList.add(new Dish("Schnitzel", R.drawable.germany_famous_dish_schnitzel));
                dishesList.add(new Dish("Hasenpfeffer", R.drawable.germany_famous_dish_hasenpfeffer));
                dishesList.add(new Dish("Bratwurst", R.drawable.germany_famous_dish_bratwurst));
                break;
        }

        return dishesList;
    }

    public static ArrayList<Attraction> getSupportedAttractionsOf(Country country) {
        ArrayList<Attraction> attractionsList = new ArrayList<>();

        for (String attrName : country.getSupportedAttractionsNames()) {
            attractionsList.add(getAttractionByName(attrName));
        }

        return attractionsList;
    }

}


























