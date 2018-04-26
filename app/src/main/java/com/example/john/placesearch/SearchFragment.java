package com.example.john.placesearch;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements View.OnClickListener {
    private View view;
    public static final String EXTRA_MESSAGE = "com.example.john.placesearch.SearchFragment.MESSAGE";
    String example_json = "{\"html_attributions\":[],\"results\":[{\"geometry\":{\"location\":{\"lat\":34.0223519,\"lng\":-118.285117},\"viewport\":{\"northeast\":{\"lat\":34.02825570000001,\"lng\":-118.2819925},\"southwest\":{\"lat\":34.01515809999999,\"lng\":-118.2944905}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"b85217d74722f6fec94a4135f209e13092d81a5e\",\"name\":\"University of Southern California\",\"photos\":[{\"height\":552,\"html_attributions\":[\"Leonard Estrada<\\/a>\"],\"photo_reference\":\"CmRaAAAAFTehCecYn9ysrwN90ZdzaSCRovnK7PVTLKH6sRUajUexzioh7q1kwifJ5VD1RvXtdU2Vtah0rEHr41sWZ1zp2HQYo7SAWynahIKfAnTJf10eHqR7e3SEBE9xILF0wczBEhCDjnbTGppO5Lgzd112X-kZGhTXjjThwJPGq2_uHrl0gdI093zbng\",\"width\":750}],\"place_id\":\"ChIJ7aVxnOTHwoARxKIntFtakKo\",\"price_level\":2,\"rating\":4.5,\"reference\":\"CmRbAAAAwTcLih70KaYZ-kaiu7EGR5bmJm98xh1QbeYU-1Wwb_FxUlEbnbyXNoue3usRbm8pUr8ah39RyqDJxDHr52Dt6NqHJBtHy1Ru7Y57kyKQB_l82XOV3JTXMpIL6eYCqKaOEhB9E6yWKZ4hqwD0UCU-x84TGhTyDR1hNuiSnlJfJ55YJADvnVm7Hg\",\"scope\":\"GOOGLE\",\"types\":[\"university\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0388653,\"lng\":-118.2585263},\"viewport\":{\"northeast\":{\"lat\":34.04018002989272,\"lng\":-118.2571132201073},\"southwest\":{\"lat\":34.03748037010728,\"lng\":-118.2598128798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/shopping-71.png\",\"id\":\"29e9c9873dba521ccb5fb4488d379b78eacfa7e8\",\"name\":\"Usc\",\"place_id\":\"ChIJGROLVcnHwoARb_c7LmECmdA\",\"reference\":\"CmRbAAAAG7I-3gkymLh6FpiWz36YtOAatjQyjY9eclvp934OGFbXgIJQ54EnFMKsvGdtQf0DKyJnAmOe7firBg5G5ZNZ3uELdCLAm5-D4SpmgfgVc3yYZow-2jX55gOJaVOMIVnhEhAAzfX5U3_Oycl6cnC9lWzKGhQxGCQDG_WpKvfR8aqyuXJkcaa7qg\",\"scope\":\"GOOGLE\",\"types\":[\"shoe_store\",\"store\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"S Main St, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0188247,\"lng\":-118.2857791},\"viewport\":{\"northeast\":{\"lat\":34.01999082989273,\"lng\":-118.2845940201073},\"southwest\":{\"lat\":34.01729117010728,\"lng\":-118.2872936798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"420291f337701a0d2efda7fa04883a4a9c52cddb\",\"name\":\"USC Marshall School of Business\",\"opening_hours\":{\"open_now\":true,\"weekday_text\":[]},\"photos\":[{\"height\":3024,\"html_attributions\":[\"Michael Watson<\\/a>\"],\"photo_reference\":\"CmRaAAAAEMoamx9SXcwFwZ-ALKK6_TpodsJBKbpDG79Kgao2OTG3cziMvUXmzg3rnwo7QUFcmzk1FLQTId2DXUlDYqyRqdQHqVwNJy20xJz33OQK6a1-3cAkZ37poCtuo4dXwAVZEhC__pCyvuYFk86x6F4kuUpHGhQPLV7ItaoQ0ckYKQsKebuFCjDWnA\",\"width\":4032}],\"place_id\":\"ChIJcapC4OLHwoARmgx_qjIv5bo\",\"price_level\":2,\"rating\":4.3,\"reference\":\"CmRbAAAAE6j1K_p0NMprBtajdtAjxkbkpuuG-TnIn2KFPxLf13VdHWRRf8TRDpsYsGyixsHLRF5IifS5rgtHrx0msrxocVM0v6VkjbECZy7I-OjyZjqMoSR-7Jvk2BYl6twHLZspEhDXtZtuQstvqxkms8kASfjJGhStjdZ1ZGiD1lGznRm69fbUbVVSrg\",\"scope\":\"GOOGLE\",\"types\":[\"university\",\"school\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"3670 Trousdale Pkwy, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0219506,\"lng\":-118.2838376},\"viewport\":{\"northeast\":{\"lat\":34.02335467989272,\"lng\":-118.2826106201073},\"southwest\":{\"lat\":34.02065502010727,\"lng\":-118.2853102798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"ff27bc590b92f482a446220508868c6f2afddaf2\",\"name\":\"USC Rossier School of Education\",\"opening_hours\":{\"open_now\":true,\"weekday_text\":[]},\"place_id\":\"ChIJka-OiePHwoARV3litr6O0JQ\",\"price_level\":2,\"rating\":4.7,\"reference\":\"CmRbAAAA0ehhykoVNMYoGRHFcJEDUmFCticskchpr5XEhI0aJ150GzseHc7u3hNHx9hJS9Ty7caDqG7M-KSFGCmyJZLHSGoOUweQzVzBFPAoUNUVJfTj5XiTWvtq72Kqa95GUbF5EhCQ3GQzZbosYNPl0nIx8eo6GhTuRNqNLQJfBLKesW3bsTKpTgL_5w\",\"scope\":\"GOOGLE\",\"types\":[\"point_of_interest\",\"establishment\"],\"vicinity\":\"3470 Trousdale Pkwy, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0192944,\"lng\":-118.2835507},\"viewport\":{\"northeast\":{\"lat\":34.02074842989272,\"lng\":-118.2821344201073},\"southwest\":{\"lat\":34.01804877010728,\"lng\":-118.2848340798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"8a686d6d058f2965e29f22d5a9ef35b4d2664fa1\",\"name\":\"USC Sol Price School of Public Policy\",\"opening_hours\":{\"open_now\":true,\"weekday_text\":[]},\"place_id\":\"ChIJyVjDS-LHwoARi9U5oaety9w\",\"price_level\":2,\"rating\":4.5,\"reference\":\"CmRbAAAAgUPpUzH4kDajCCbFn_61SBHbsvtBzaVMR7BoUVFOr6XgMgxt5TdSWdiZCIQA20yZHxBRG5AsgXTbvoTNNy56dnyETH_1Bj9LKTOC-98CipDsdqDGOMKw4TNmBA4-jy_IEhDHTx49_QQdNc2AQBTYazqSGhSLSLClSpVKHHfYomh3q29D4-gSFg\",\"scope\":\"GOOGLE\",\"types\":[\"point_of_interest\",\"establishment\"],\"vicinity\":\"650 Childs Way, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0219289,\"lng\":-118.2847286},\"viewport\":{\"northeast\":{\"lat\":34.02321117989272,\"lng\":-118.2832278701073},\"southwest\":{\"lat\":34.02051152010728,\"lng\":-118.2859275298927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"1225f8227361c0f3500eb1d70318d5cb30ff08e5\",\"name\":\"USC Dornsife College of Letters, Arts and Sciences\",\"opening_hours\":{\"weekday_text\":[]},\"place_id\":\"ChIJX1NVnOHHwoARteMITdTuCpE\",\"price_level\":2,\"rating\":4,\"reference\":\"CmRbAAAA-y3QRe8hXr0xjGpgBcsjWa5Z-Bwx8JCR1uKXyfqiK-jEYIlVEOsnWw0JsKvzIHS-fh9tq7Sbt0jXMgTylihQskRSLDWcvbzmXaEf3gdL_njFqLUq3xXuJgqiU6jS98zlEhAfDg12oviPvGXPeajlXRF9GhS1JbvyLJTvwE5O-DCWJOk7yNPYpw\",\"scope\":\"GOOGLE\",\"types\":[\"point_of_interest\",\"establishment\"],\"vicinity\":\"3551 Trousdale Pkwy, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0231176,\"lng\":-118.2854408},\"viewport\":{\"northeast\":{\"lat\":34.02455267989272,\"lng\":-118.2840365701073},\"southwest\":{\"lat\":34.02185302010728,\"lng\":-118.2867362298928}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"acd3451f667d7a664ae35b95a2688d3631601051\",\"name\":\"USC Thornton School of Music\",\"photos\":[{\"height\":3024,\"html_attributions\":[\"Thiago Perrotta<\\/a>\"],\"photo_reference\":\"CmRaAAAAqVrrx2RQjce82iomEL2l4QeZx61DplAqS3NGPifWjG-hLNujIb4_PS2fusbwrScqt6IYw0ls0_2TdsxG_gU8SB2Uu6ZTFZ6Mpc_nDJvqP0w_chU_SGJ712MMfCOQyREnEhAU2QN3L4KriWEGW3oVRjXHGhQ9L03MIbCINHTFJgFrekeU9zCfaw\",\"width\":4032}],\"place_id\":\"ChIJSYWpjOTHwoARywRPDpIOS5k\",\"price_level\":2,\"rating\":4.7,\"reference\":\"CmRbAAAA-9PBRwjupHNo6EmKU6eE8lljzkyagWccIDvgMeS7YUVxbjSDC0YNZQOYBZoV_mmQOW2FitpDiDVjN9wbGokxyP8DtqHzHwWcB3wQY7w_yNv0qE2L8MSRgskDC6ht37iyEhBYLg7ZsDPi0dsJ8H06jNnQGhSe_80F4Kn63PHRY4uk7ps2z0MLAg\",\"scope\":\"GOOGLE\",\"types\":[\"university\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"840 W 34th St, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0290711,\"lng\":-118.2796217},\"viewport\":{\"northeast\":{\"lat\":34.03068922989272,\"lng\":-118.2781001201073},\"southwest\":{\"lat\":34.02798957010728,\"lng\":-118.2807997798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"8a2615e81b6b2304c2becdc8b561d8fff240f31e\",\"name\":\"USC Kerckhoff Hall\",\"opening_hours\":{\"open_now\":true,\"weekday_text\":[]},\"photos\":[{\"height\":728,\"html_attributions\":[\"Neil Teixeira<\\/a>\"],\"photo_reference\":\"CmRaAAAAzjCIzK896IiM0mP95zufhkrZi6XbseNH8qOhWaoqZ5M27O_nN5nmYLmXbn2x5our7FgZ-Mo83WzGzVWT1pgXCVU6lK74D7Hq6lnyPi0Bgt6Gyn0j1-uz5IO3DrcpcMpBEhDKQznT5hkRhHGkovaJmL1uGhQ1lwGoskYs9NOHEv-V3H5QawpFpg\",\"width\":1024}],\"place_id\":\"ChIJ_WU2jujHwoARipxeuWi3fyM\",\"price_level\":2,\"reference\":\"CmRbAAAAQGsnBEGYXmaRrCMuUjjFHgo8iMDC04shCe8hOll62__XK6nCEsX0dBT6JQ1kW42yuMKZbowyKP3bmFcqvdWZHhZlEkem2ct62YmRpCU9cjUI43sKLE_bFuOVrpeVDmtDEhAqKbJh7wet6DTNoelg8JrHGhSKkGSpTKIXOR-oCd58RtPhHNQbUQ\",\"scope\":\"GOOGLE\",\"types\":[\"school\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"90089, 734 W Adams Blvd, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0231926,\"lng\":-118.2896219},\"viewport\":{\"northeast\":{\"lat\":34.02447377989272,\"lng\":-118.2888367},\"southwest\":{\"lat\":34.02177412010727,\"lng\":-118.2919151000001}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/generic_business-71.png\",\"id\":\"aab76435d0b3f89660b432724620caae3ac14021\",\"name\":\"USC Dedeaux Stadium\",\"photos\":[{\"height\":2988,\"html_attributions\":[\"rey avila<\\/a>\"],\"photo_reference\":\"CmRaAAAAWfeFwbD9hSBgP9YgiUaG7phXpJvj2Pk_FChA8u7rKtHTJXU_dueyIqmMHPTgUA0t0A_JCADioF7ykCkCELiLD7qBbKxmSUL6B9R2A0nQmiJZu-iTnSMCyl7Sx5yGHw8DEhDoswovGU89CW-Etu3EFs25GhTk97DfatagpHJiI9wCE79w62DfJw\",\"width\":3984}],\"place_id\":\"ChIJ6bzD7_rHwoARNwZHmE4XR7Q\",\"price_level\":2,\"rating\":4.2,\"reference\":\"CmRbAAAAi7c64xZTEX6NVoC5IICF2Fqc5IBZ3jtEhs3rCmACi0RhxSYz-ShvWO0BinzELA4nQKXX26JxHmLIXzlNTwb-uKC72j_rCbZMMolNdqr-yNZ5izOy1_N_MtX42Iel3v39EhB_XlqU51U16EwMM4VySWOLGhTEOhU3oa3iVFblGtyMEjjFa1tX0A\",\"scope\":\"GOOGLE\",\"types\":[\"stadium\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"1031 Childs Way, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0186751,\"lng\":-118.2842727},\"viewport\":{\"northeast\":{\"lat\":34.02002492989272,\"lng\":-118.2829228701073},\"southwest\":{\"lat\":34.01732527010727,\"lng\":-118.2856225298927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"a896816956d277575bee97e399a02e08c917b2df\",\"name\":\"USC Gould School of Law\",\"opening_hours\":{\"weekday_text\":[]},\"photos\":[{\"height\":1365,\"html_attributions\":[\"USC Gould School of Law<\\/a>\"],\"photo_reference\":\"CmRaAAAADMxOOBNfyF6Ygx0v_kWxyxvaUs9I6om-EenDRxbKJkHqYGtY71daVw_sVCyi8yabNTciJt20Q86_EH2WyI1qOynzpcqKlZZiXUwslZpAjpoMKT57y256mXQbYTDckNexEhCexf-dbBCbJeYjLnuD1APrGhSKR1zZBPeh7A0RhnWVdKO8R6cO0A\",\"width\":2048}],\"place_id\":\"ChIJ7aVxnOTHwoARksAnB1bKfHw\",\"price_level\":2,\"rating\":4.6,\"reference\":\"CmRbAAAAtw4x_jWrJQEsPiwDUU9cvbym7fAyKfC0CA7SJ9njoUWsWK-1G33WyAfXXzje_jnSpr9jds8LWHOg5Kvt12rUO-YasaHYYbpTs4b-B_oz8CUbNr9IsAZZpQfl85Vi4fuKEhBpkwl4b5AGWMudXuswGiaJGhTZwQWUafN7Lj0Kecwx-Jm-yqCsGQ\",\"scope\":\"GOOGLE\",\"types\":[\"university\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"699 Exposition Blvd, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0234463,\"lng\":-118.2871493},\"viewport\":{\"northeast\":{\"lat\":34.02508557989272,\"lng\":-118.2856920201073},\"southwest\":{\"lat\":34.02238592010728,\"lng\":-118.2883916798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"dcdf991751e3a7a305ee2fbe972d9935eaa91b7c\",\"name\":\"USC School of Cinematic Arts\",\"opening_hours\":{\"weekday_text\":[]},\"photos\":[{\"height\":1893,\"html_attributions\":[\"ralph scott<\\/a>\"],\"photo_reference\":\"CmRaAAAAB_HuyA1stuE3_3MxGeeete8XcvdQz2HU3KDA7fdkCbFNSmngiMkuavFJ3E_f6CXUhcYTkovyqDzRhLcz1UA2PIVHLsM5I5XHtAlZbxbmKhdUnIU0iwmyVOw4LBjG3aEIEhDk9nhZQmqAXPE45t-0ZUsSGhSw0UXZGHIhlLlphiQhOtI0JbPCkw\",\"width\":3834}],\"place_id\":\"ChIJDcIm7eTHwoARAbescWkwKrk\",\"price_level\":2,\"rating\":4.7,\"reference\":\"CmRbAAAAlGvA1fE9oQd-3VmiA7r3I_LKDI4MEm35PU0jDZofdoASEq_eberYa5uy7NgfkkjVI6fzhI-G9hVhadzLXoluHru8iiFMhV4XozGS3CeC4cGGoMhTj1ffHdkipOyZri_pEhA2GWMruRSwQNMl_GOXt31jGhTvlJ27syogOTiA7R5uIAsfFP1akw\",\"scope\":\"GOOGLE\",\"types\":[\"university\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"900 W 34th St, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0254248,\"lng\":-118.2851865},\"viewport\":{\"northeast\":{\"lat\":34.02682082989271,\"lng\":-118.2832280201073},\"southwest\":{\"lat\":34.02412117010727,\"lng\":-118.2859276798928}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/generic_business-71.png\",\"id\":\"a679f3bbba60fffc05e2613bfeca1cd5b5a99ca0\",\"name\":\"USC Village\",\"photos\":[{\"height\":2988,\"html_attributions\":[\"Spencer Albrecht<\\/a>\"],\"photo_reference\":\"CmRaAAAAcIkGt8c9fMtxdNWByn-GNG6LuvrH4FOvVINklu0wzjvKfgtUXHwPPnMVM1LMRNLZswYwPsLf8pB7RYF9-4iWm4IAsfrCb26447nTIqGv6Yfce0si9xtIAmmhti5J8MJxEhAHHAs-2L030inZcZ3xEK7GGhTQOuAdNLAtRpJVJrpqd7Sc-08cHg\",\"width\":5312}],\"place_id\":\"ChIJpSiADOXHwoARKaj3gaYiAQI\",\"price_level\":2,\"rating\":4.7,\"reference\":\"CmRbAAAA5c5HIW9ab7jRcNlEXPV2WLVZLLnY51mY_RYkpQ42kmVlNKbJe71R3AP3_IjBQ2yeEaChx3F-FT66SKSUq17_lm-kcCr3yiP6yGjI2_vqQk2IhNN0gO38aHhGqTVFAA9PEhDesMJh5HgB-SJ_Pw0aMAERGhQVmrRhE9WAg8g1LkSlrKeXD1LGDw\",\"scope\":\"GOOGLE\",\"types\":[\"point_of_interest\",\"establishment\"],\"vicinity\":\"3301 S Hoover St, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0191869,\"lng\":-118.2880171},\"viewport\":{\"northeast\":{\"lat\":34.02067867989273,\"lng\":-118.2865777201073},\"southwest\":{\"lat\":34.01797902010728,\"lng\":-118.2892773798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"114d54fa22ef062fe0cf8e2ce5b1201609614530\",\"name\":\"USC School of Architecture\",\"photos\":[{\"height\":708,\"html_attributions\":[\"Sau Hei Lam<\\/a>\"],\"photo_reference\":\"CmRaAAAAJIodmqVbgJ82jEYVjQg0SIRTGBC1J4z2c5vPDgWAqu3OsCSaZ4jytuUyvVrv7d-erjo0PKlhlTpv2g9gvKFwqs0RS8P8IHpnyqGN9syTEcz-73Qu2UctqPmW46pIv3aSEhBwYdt4lTFdzZNMeaxvOeaLGhSqtHiyT7rvld-VupZHahSV3pXWPw\",\"width\":1090}],\"place_id\":\"ChIJzwjszfzHwoAR5Iw3jQ3a7f4\",\"price_level\":2,\"rating\":5,\"reference\":\"CmRbAAAARi2GSk27p8IWuJOf5yJfM8EHIkr8bM3V1W5UzjstaQFeers1TyMLbNCNR_Jh46G4_gEFqN0HgJqM8jDyG8wTjRQWIs5eL6u2j4qUTYCOFcre-zI3Dkzko4Ew1otutdZdEhBzzEVe_CyTN6mEJIvqUWqAGhS8uJazUAAV9jhe2BxVG4VB7Y_POg\",\"scope\":\"GOOGLE\",\"types\":[\"point_of_interest\",\"establishment\"],\"vicinity\":\"850 Bloom Walk, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0188155,\"lng\":-118.2873177},\"viewport\":{\"northeast\":{\"lat\":34.02008797989271,\"lng\":-118.2858941701073},\"southwest\":{\"lat\":34.01738832010727,\"lng\":-118.2885938298927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/museum-71.png\",\"id\":\"aeb36fe00bfde95e614a5be7d36de885bf5a7784\",\"name\":\"USC Fisher Museum of Art\",\"opening_hours\":{\"open_now\":false,\"weekday_text\":[]},\"photos\":[{\"height\":2988,\"html_attributions\":[\"A Google User<\\/a>\"],\"photo_reference\":\"CmRaAAAALo2erEibsTtd6dFfh9iuSidOPS2uNng6esMV9qsZKNUkj6txTc7QV4RuR_ZptYXwmfQXbo329Y36kXCWOBFhetQwPG7oClrlEo2nsS68hqcE-ceIueCoAK0-nFc2Rl62EhDSrfjAEVEcLDaUq_NFGF5mGhQuNaWLxgi9QxK0skQkEwfFkg18mQ\",\"width\":5312}],\"place_id\":\"ChIJ7wU93vzHwoARWzQtB31pxE8\",\"price_level\":2,\"rating\":4.4,\"reference\":\"CmRbAAAAqwHmX6yt7GSQbv1J4dPOHczI44qTPRprvVde37-cJJsPEACHYrIQ6PMVoqS5d1suJYiF8dmWbYfVzJhRjoYES0dHexa-V_tTFz1LAnk8spDsqgBVb4JWh1P9Mo5LMapdEhBNraFzcLDRgWaVY0TONWJ9GhTJQf7OreR38tnLaERyABqxB5e1mw\",\"scope\":\"GOOGLE\",\"types\":[\"museum\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"823 W Exposition Blvd, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0203571,\"lng\":-118.2888131},\"viewport\":{\"northeast\":{\"lat\":34.02192597989273,\"lng\":-118.2879694201073},\"southwest\":{\"lat\":34.01922632010729,\"lng\":-118.2906690798928}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"ab53c902bf17383815a2b9a73cbd0e73a7541d77\",\"name\":\"USC Viterbi School of Engineering\",\"photos\":[{\"height\":2204,\"html_attributions\":[\"Paul Chan<\\/a>\"],\"photo_reference\":\"CmRaAAAAtyzGIrS4VN_r9zZQaVFqJhebzj-uDkWA55lVCSHsrtggnpsl_8_xTNs5uGgvpVRfybsY92gQhOi7GitwQjezcTgd7SW9BKhWRujMj-WdUqkoAZOaMjTbz96oJWfTtZSwEhCPdXY8DfNlYQX50HJGxRmJGhTD0hohzrMo2nCgiVg8NlYNnVHQag\",\"width\":3920}],\"place_id\":\"ChIJLTznYfvHwoARKXuvMlgK_eY\",\"price_level\":2,\"rating\":4.9,\"reference\":\"CmRbAAAAk2TOMjvXnjnE5KIVmxwYOq47cXXuf3df-WxoCEV20DBeczOeGD_mk9sayAFR2PiEh17h8gcpXIq-gcsbpH8EDr_hjHhiNTAoQkNClT8tr2p9AxU5Zco89zH8-dJt6dyhEhBNSmZF9Dzd1Cr9W8Bs-RsxGhSQGb28EZZ-8Ws3DZQ7SonM3vd-UA\",\"scope\":\"GOOGLE\",\"types\":[\"university\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"3650 McClintock Ave, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.022333,\"lng\":-118.282484},\"viewport\":{\"northeast\":{\"lat\":34.02356517989272,\"lng\":-118.2812099201073},\"southwest\":{\"lat\":34.02086552010728,\"lng\":-118.2839095798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"e1c589676caec1d005fb3424b163143f331f4949\",\"name\":\"USC Dworak-Peck School of Social Work\",\"photos\":[{\"height\":3036,\"html_attributions\":[\"Louie Verse<\\/a>\"],\"photo_reference\":\"CmRaAAAAUq0fw4-UZy8CYvwm2r5BfuuOs-jvrZ7a1OtCHUex9UKJV96uH50bedNPEkMWY7lbK1zEuQHavfxWJav54ywrdF4ppl5sQmUiBkEJLDt_pkpQu1fHmsXXeNqqmgq9l49pEhBGITjvET1LItGX-VzXsdxWGhSAtrl4wJAhnBlSdPrYgCJTCDI2Bw\",\"width\":4048}],\"place_id\":\"ChIJKTV_IOXHwoARfLsi1TFKrNE\",\"price_level\":2,\"rating\":4.4,\"reference\":\"CmRbAAAAhSbzNHNG6RJKvmrroGawmIhNsGNU5Oz8SIZQDd-nX73cCSO-0jI-gq8_uGuxgshWnHG5CQcUgiNhSz4_hm8Cp7zCZHidko2HQSGlMQfhinIQ1Vx6YHFrLgXc7H_-FA1_EhCmjX8lleWG9etRDc28KMpTGhRYOVXerec3oGqQ0-Vhxmnz55nfVg\",\"scope\":\"GOOGLE\",\"types\":[\"point_of_interest\",\"establishment\"],\"vicinity\":\"669 W 34th St, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0197003,\"lng\":-118.2846144},\"viewport\":{\"northeast\":{\"lat\":34.02117932989272,\"lng\":-118.2831823201073},\"southwest\":{\"lat\":34.01847967010728,\"lng\":-118.2858819798927}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/generic_business-71.png\",\"id\":\"8cfd83f965a6aa1d04c07b0a49ad3f8345e651f8\",\"name\":\"USC Newman Recital Hall\",\"place_id\":\"ChIJnQp0qePHwoAROcURCy15nWw\",\"price_level\":2,\"rating\":5,\"reference\":\"CmRbAAAAVcM72BTRTsPfnJhP0kryg_k_4WWDFfWlUov_H0sF_UUxUV-u6tcLvUgfIEts09W-kTmWQYoTKAeyDXCEV6jW5QqKj7SA1LjjJWzNX6Bmp8cDx1VC4ZVFT_E6MJKZtJb2EhDumLLHCbB1GVPwYxO9Ua2gGhQm8l3lDhkf54A7zJA7RbHoqfapXg\",\"scope\":\"GOOGLE\",\"types\":[\"point_of_interest\",\"establishment\"],\"vicinity\":\"700 Childs Way, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0194281,\"lng\":-118.2868668},\"viewport\":{\"northeast\":{\"lat\":34.02062922989272,\"lng\":-118.2856111701073},\"southwest\":{\"lat\":34.01792957010728,\"lng\":-118.2883108298928}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"bdfeda5dbdd0d30631ea0f875cca9660c95a9b27\",\"name\":\"USC Roski School of Fine Arts\",\"opening_hours\":{\"open_now\":true,\"weekday_text\":[]},\"photos\":[{\"height\":480,\"html_attributions\":[\"Ralph Spencer Steenblik<\\/a>\"],\"photo_reference\":\"CmRaAAAAfJEf0mNbwomvxdoUTlpHTkCoIh6DNzQypJXJig0X1_Kpd3n6DvVpEPxib9BCaXD0PQaiEYWzdHfeN-pcPrNLReXjTfOmgDPfH1cLqiG_MO9YZvfKziU45rOqDf_H-v4QEhC2vAcDtOvTf8tUc9_DyEFNGhSXX8EYYnH-qM6-O_-8RZANRQuCfA\",\"width\":640}],\"place_id\":\"ChIJJ20vLePHwoAR05tQK3yPvRg\",\"price_level\":2,\"rating\":2,\"reference\":\"CmRbAAAAIJvWqnQvrCRWQYPr03gJhFQfKZ24O2MugeLQ1BpZC9nUqF4EO8_xiyjM0yf82Ut7ROBw_W6yL8ssfAzCQSvXXjyXL_u2uz6otX0d_VZgOgKkk2FPpJcZ79yTSmYqNGwlEhD9Pv-6C3dOEEkzu-SXqMhQGhRfVHPcy29uL9uFYCQCKOdSbMSpgw\",\"scope\":\"GOOGLE\",\"types\":[\"university\",\"school\",\"point_of_interest\",\"establishment\"],\"vicinity\":\"825 Bloom Walk, Los Angeles\"},{\"geometry\":{\"location\":{\"lat\":34.0217032,\"lng\":-118.2805467},\"viewport\":{\"northeast\":{\"lat\":34.02299637989271,\"lng\":-118.2790669701073},\"southwest\":{\"lat\":34.02029672010727,\"lng\":-118.2817666298928}}},\"icon\":\"https:\\/\\/maps.gstatic.com\\/mapfiles\\/place_api\\/icons\\/school-71.png\",\"id\":\"655f0fb2063b3cf7f84ecb3d028ceef8df72d9d8\",\"name\":\"USC International Academy\",\"opening_hours\":{\"weekday_text\":[]},\"photos\":[{\"height\":2988,\"html_attributions\":[\"Aziz almuusawi<\\/a>\"],\"photo_reference\":\"CmRZAAAAIlJP0o5qsF0IUyiRm55yLfq_tyQte4m3WB4KsUHHL4O9zk02OS31bLaatxWuLTSRd1n5yH5HSrjdyddf80NDGU3R8vwdZQYKM7JqQlJPmHxddEeqaQ4Ou7OIFFTCCz-8EhAKEFzwEZBqZo3E08_21qvHGhTvQCH1XH7wDNP9DW_asKs1yX3trw\",\"width\":5312}],\"place_id\":\"ChIJR12QeeHHwoARShH4bkfNIOk\",\"price_level\":2,\"rating\":5,\"reference\":\"CmRbAAAACf-CfkKk31-IMLm8LtGDRSIWgZ-Ppey8saEUReUNSVxwffN50-bSBOE1tpw5B6KSZ4lETcMPKJ66ALcAxQGOe-hLgdD4IbC1LiPE8dpiyzM7VJDMy9_4FF7uQfXel190EhBlrleQLKhpWMsvQPWaVCRkGhSsYZo1O5bPgQ8YJkDRNkicfX6ahg\",\"scope\":\"GOOGLE\",\"types\":[\"point_of_interest\",\"establishment\"],\"vicinity\":\"3415 S Figueroa St, Los Angeles\"}],\"status\":\"OK\",\"lat\":\"34.0266\",\"lng\":\"-118.2831\"}";

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        this.view = v;

        Button b = (Button) v.findViewById(R.id.button3);
        b.setOnClickListener(this);
        Button bClear = (Button) v.findViewById(R.id.button4);
        bClear.setOnClickListener(this);

        ((AutoCompleteTextView)view.findViewById(R.id.autocompeletelocation)).setEnabled(false);

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radiongrouplocation);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if(checkedId == R.id.radioButton){
                    ((AutoCompleteTextView)view.findViewById(R.id.autocompeletelocation)).setEnabled(false);
                }
                else if(checkedId == R.id.radioButton2){
                    ((AutoCompleteTextView)view.findViewById(R.id.autocompeletelocation)).setEnabled(true);
                }
            }
        });

        //Set adapter for autocomplete text view
        final AutoCompleteTextView searchPlace = view.findViewById(R.id.autocompeletelocation);

        CustomAutoCompleteAdapter adapter =  new CustomAutoCompleteAdapter(getContext());
        searchPlace.setAdapter(adapter);
        searchPlace.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //do something with the selection
                searchPlace.setText(""+adapterView.getItemAtPosition(i));
            }
        });

        return v;
    }
    public void searchScreen(){
        Intent i = new Intent();
        i.setClass(getContext(), MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                //validation
                String searchURL = formValidate();
                if( searchURL != null){
                    searchAndGo(searchURL);
                }
                break;
            case R.id.button4:
                clear();
                break;
        }
    }

    private void clear() {
        //clear error message
        TextView textKeywordError = (TextView)this.view.findViewById(R.id.textviewkeyworderror);
        textKeywordError.setVisibility(View.GONE);
        TextView textLocationError = (TextView)this.view.findViewById(R.id.textviewlocationerror);
        textLocationError.setVisibility(View.GONE);

        //set keyword
        ((TextView)this.view.findViewById(R.id.edittextkeyword)).setText("");
        //set spinner
        ((Spinner)this.view.findViewById(R.id.spinner2)).setSelection(0);
        //set distance
        ((TextView)this.view.findViewById(R.id.edittextdistance)).setText("");
        //set radio
        ((RadioGroup)this.view.findViewById(R.id.radiongrouplocation)).check(R.id.radioButton);
        //set location
        ((AutoCompleteTextView)this.view.findViewById(R.id.autocompeletelocation)).setText("");
    }

    private String formValidate() {
        boolean flag = false;
        String keyword = ((TextView)this.view.findViewById(R.id.edittextkeyword)).getText().toString().trim();
        if(keyword.length() == 0){
            TextView textKeywordError = (TextView)this.view.findViewById(R.id.textviewkeyworderror);
            textKeywordError.setVisibility(View.VISIBLE);
            flag = true;
        }
        String location = ((AutoCompleteTextView)this.view.findViewById(R.id.autocompeletelocation)).getText().toString().trim();
        if(location.length() == 0){
            TextView textLocationError = (TextView)this.view.findViewById(R.id.textviewlocationerror);
            textLocationError.setVisibility(View.VISIBLE);
            flag = true;
        }
        if(flag){
            return null;
        }
        return "http://webhw8.us-east-2.elasticbeanstalk.com/server.php?keyword=coffe&category=default&distance=10&location=user&lat=34.0266&lng=-118.2831";
    }

    private void searchAndGo(String searchURL) {
        final ProgressDialog progress = new ProgressDialog(getContext());
        progress.setMessage("Fetching results");
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url =searchURL;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progress.dismiss();
                        searchPlaceList(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //
            }
        });
        progress.show();
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    /** Called when the user taps the Send button */
    public void searchPlaceList(String jsonString) {
        Intent intent = new Intent(this.getActivity(), PlaceListActivity.class);

        //get all the information from form
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, jsonString);
        startActivity(intent);
    }

    class MyTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog progress;

        public MyTask(ProgressDialog progressdialog) {
            this.progress = progressdialog;
        }

        public void onPreExecute() {
            progress.show();
        }

        public Void doInBackground(Void... unused) {
            //
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        public void onPostExecute(Void unused) {
            progress.dismiss();
//            searchPlaceList();
        }
    }

}
