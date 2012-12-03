/**
 * (c) Copyright 2011 TeleNav.
 * All Rights Reserved.
 */
package com.telenav.cserver.html.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * fetch dummy data
 * @author  xljiang@telenav.cn
 * @version 1.0 2011-12-27
 */

public class DummyDataAction extends HtmlPoiBaseAction{
	private static String defaultData="{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":true,\"hasPoiExtraAttributes\":true,\"hasAdsMenu\":false,\"hasPoiDetails\":false,\"supplimentInfos\":[{\"supportInfo\":\"[87]\",\"price\":\"$3.889\"},{\"supportInfo\":\"[89]\",\"price\":\"$4.059\"},{\"supportInfo\":\"[91]\",\"price\":\"$4.059\"}],\"hasReviews\":false,\"type\":196,\"isRatingEnable\":true,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1590\",\"price\":\"$3.889\",\"phoneNumber\":\"4087301892\",\"poiId\":\"3000620744\",\"categoryId\":\"30019\",\"brand\":\"COSTCO- SUNNYVALE #423\",\"distanceWithUnit\":\"1.1 mi\"},\"isAdsPoi\":false,\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":0,\"phoneNumber\":\"4087301892\",\"status\":1,\"label\":\"COSTCO- SUNNYVALE #423\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String gas = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94085\",\"lon\":-12202632,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"117 W MAUDE AVE\",\"type\":0,\"lat\":3738852,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":false,\"hasAdsMenu\":false,\"hasPoiDetails\":false,\"supplimentInfos\":[{\"supportInfo\":\"[87]\",\"price\":\"$3.739\"},{\"supportInfo\":\"[89]\",\"price\":\"$3.839\"},{\"supportInfo\":\"[91]\",\"price\":\"$3.939\"},{\"supportInfo\":\"[D]\",\"price\":\"$3.939\"}],\"hasReviews\":false,\"type\":196,\"isRatingEnable\":false,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"stop\":{\"zip\":\"94085\",\"lon\":-12202632,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"117 W MAUDE AVE\",\"type\":0,\"lat\":3738852,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1742\",\"price\":\"$3.739\",\"phoneNumber\":\"4087367816\",\"poiId\":\"60389384\",\"categoryId\":\"30019\",\"brand\":\"WORLD OIL #50\",\"distanceWithUnit\":\"1.1 mi\"},\"isAdsPoi\":false,\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94085\",\"lon\":-12202632,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"117 W MAUDE AVE\",\"type\":0,\"lat\":3738852,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":5,\"phoneNumber\":\"4087367816\",\"status\":1,\"label\":\"WORLD OIL #50\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String menuExtra = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":true,\"hasPoiExtraAttributes\":true,\"hasAdsMenu\":false,\"hasPoiDetails\":false,\"supplimentInfos\":[{\"supportInfo\":\"[87]\",\"price\":\"$3.889\"},{\"supportInfo\":\"[87]\",\"price\":\"$3.889\"},{\"supportInfo\":\"[89]\",\"price\":\"$4.059\"},{\"supportInfo\":\"[89]\",\"price\":\"$4.059\"},{\"supportInfo\":\"[91]\",\"price\":\"$4.059\"},{\"supportInfo\":\"[91]\",\"price\":\"$4.059\"}],\"hasReviews\":false,\"type\":196,\"isRatingEnable\":true,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1590\",\"price\":\"$3.889\",\"phoneNumber\":\"4087301892\",\"poiId\":\"3000620744\",\"categoryId\":\"2041\",\"brand\":\"COSTCO- SUNNYVALE #423\",\"distanceWithUnit\":\"1.1 mi\"},\"isAdsPoi\":false,\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":0,\"phoneNumber\":\"4087301892\",\"status\":1,\"label\":\"COSTCO- SUNNYVALE #423\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String sponsor = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199644,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"\",\"type\":0,\"lat\":3737290,\"city\":\"Sunnyvale\",\"country\":\"\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":false,\"hasAdsMenu\":true,\"hasPoiDetails\":false,\"menu\":\"<bold>Misticanza Di Campo Con Scagulie Di Parmigiano</bold>?sweet and bitter green salad ...... 10.00 <bold>Frittura Di Calamari</bold>\",\"hasReviews\":false,\"type\":127,\"openTableInfo\":{\"isReservable\":true,\"partner\":\"openTable\"},\"isRatingEnable\":false,\"hasDeals\":true,\"coupons\":[{\"description\":\"If you have dinner with us, bring in your dinner receipt and receive 10% off your lunch on Friday, Saturday and Sunday. We look forward to seeing you soon for lunch and dinner!\",\"endDate\":\"4509072000000\"}],\"isSponsorPoi\":true,\"bizPoi\":{\"brand\":\"fakevbb1\",\"stop\":{\"zip\":\"94086\",\"lon\":-12199644,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"\",\"type\":0,\"lat\":3737290,\"city\":\"Sunnyvale\",\"country\":\"\"},\"distance\":\"1427\",\"distanceWithUnit\":\"0.9 mi\",\"phoneNumber\":\"4155650360\",\"poiId\":\"50000000008\",\"categoryId\":\"0\"},\"ad\":{\"adLine\":\"Enjoy authenitc Brazilian Cuisine\",\"adSource\":\"CS\",\"adID\":\"1202058\"},\"isAdsPoi\":false,\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"openTable\":{\"isReservable\":true,\"partner\":\"openTable\"},\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94086\",\"lon\":-12199644,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"\",\"type\":0,\"lat\":3737290,\"city\":\"Sunnyvale\",\"country\":\"\"},\"sharedFromUser\":\"\",\"selectedIndex\":11,\"phoneNumber\":\"4155650360\",\"status\":1,\"label\":\"fakevbb1\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String showtime = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":false,\"hasAdsMenu\":false,\"hasPoiDetails\":false,\"hasReviews\":false,\"type\":196,\"isRatingEnable\":true,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1590\",\"price\":\"$3.889\",\"phoneNumber\":\"4087301892\",\"poiId\":\"19000005375\",\"categoryId\":\"181\",\"brand\":\"COSTCO- SUNNYVALE #423\",\"distanceWithUnit\":\"1.1 mi\"},\"isAdsPoi\":false,\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":0,\"phoneNumber\":\"4087301892\",\"status\":1,\"label\":\"COSTCO- SUNNYVALE #423\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String ads = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94085\",\"lon\":-12202956,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"600 N MATHILDA AVE\",\"type\":0,\"lat\":3739267,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":true,\"hasAdsMenu\":false,\"hasPoiDetails\":false,\"hasReviews\":false,\"type\":196,\"isRatingEnable\":true,\"hasDeals\":true,\"isSponsorPoi\":false,\"bizPoi\":{\"brand\":\"Best Western Silicon Valley Inn\",\"stop\":{\"zip\":\"94085\",\"lon\":-12202956,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"600 N MATHILDA AVE\",\"type\":0,\"lat\":3739267,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"2258\",\"distanceWithUnit\":\"1.4 mi\",\"phoneNumber\":\"\",\"poiId\":\"3411883174\",\"categoryId\":\"595\"},\"isAdsPoi\":true,\"categoryLogo\":\"/tnimages/logo/cat-accomodation.png\",\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94085\",\"lon\":-12202956,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"600 N MATHILDA AVE\",\"type\":0,\"lat\":3739267,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":1,\"phoneNumber\":\"\",\"status\":1,\"label\":\"Best Western Silicon Valley Inn\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String sponsorWithAD = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94085\",\"lon\":-12199511,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1214 Apollo Way #404\",\"type\":0,\"lat\":3737915,\"city\":\"Sunnyvale\",\"country\":\"\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":false,\"hasAdsMenu\":false,\"hasPoiDetails\":false,\"menu\":\"\",\"hasReviews\":false,\"type\":127,\"isRatingEnable\":false,\"hasDeals\":false,\"isSponsorPoi\":true,\"bizPoi\":{\"brand\":\"Grand Indian Buffett\",\"stop\":{\"zip\":\"94085\",\"lon\":-12199511,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1214 Apollo Way #404\",\"type\":0,\"lat\":3737915,\"city\":\"Sunnyvale\",\"country\":\"\"},\"distance\":\"1380\",\"distanceWithUnit\":\"0.9 mi\",\"phoneNumber\":\"4084810700\",\"poiId\":\"999000028648\",\"categoryId\":\"0\"},\"ad\":{\"adLine\":\"This is test copy for VBB.  No words should be truncated and all words should be visible.  The category icon should agree with the type of business being advertised.\",\"adSource\":\"TN\",\"adID\":\"3664529\"},\"isAdsPoi\":false,\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"openTable\":{\"isReservable\":true,\"partner\":\"openTable\"},\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94085\",\"lon\":-12199511,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1214 Apollo Way #404\",\"type\":0,\"lat\":3737915,\"city\":\"Sunnyvale\",\"country\":\"\"},\"sharedFromUser\":\"\",\"selectedIndex\":0,\"phoneNumber\":\"4084810700\",\"status\":1,\"label\":\"Grand Indian Buffett\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String new4IPhone = "{\"poi\":{\"bizPoi\":{\"phoneNumber\":\"4087301892\",\"poiId\":\"3000620744\",\"categoryId\":\"30019\",\"brand\":\"COSTCO- SUNNYVALE #423\",\"distanceWithUnit\":\"1.1 mi\"},\"adsId\":\"13434\",\"rating\":0,\"rateNumber\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"}}";
	private static String hotel = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":false,\"hasAdsMenu\":false,\"hasPoiDetails\":false,\"hasReviews\":false,\"type\":196,\"isRatingEnable\":false,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1590\",\"price\":\"$3.889\",\"phoneNumber\":\"4087301892\",\"poiId\":\"3000620744\",\"categoryId\":\"595\",\"brand\":\"COSTCO- SUNNYVALE #423\",\"distanceWithUnit\":\"1.1 mi\"},\"isAdsPoi\":false,\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":0,\"phoneNumber\":\"4087301892\",\"status\":1,\"label\":\"COSTCO- SUNNYVALE #423\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String openTable = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":false,\"hasAdsMenu\":false,\"openTable\":{\"isReservable\":true,\"partner\":\"openTable\",\"partnerPoiId\":\"iddd\"},\"hasPoiDetails\":false,\"hasReviews\":false,\"type\":196,\"isRatingEnable\":false,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1590\",\"price\":\"$3.889\",\"phoneNumber\":\"4087301892\",\"poiId\":\"3000620744\",\"categoryId\":\"250\",\"brand\":\"COSTCO- SUNNYVALE #423\",\"distanceWithUnit\":\"1.1 mi\"},\"isAdsPoi\":false,\"rating\":0,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":0,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94086\",\"lon\":-12199559,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"150 LAWRENCE STATION RD\",\"type\":0,\"lat\":3737114,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":0,\"phoneNumber\":\"4087301892\",\"status\":1,\"label\":\"COSTCO- SUNNYVALE #423\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String postOffice = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94086\",\"lon\":-12203224,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"141 S TAAFFE ST\",\"type\":0,\"lat\":3737744,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":true,\"hasAdsMenu\":false,\"hasPoiDetails\":true,\"hasReviews\":true,\"type\":196,\"isRatingEnable\":true,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"brand\":\"US Post Office\",\"stop\":{\"zip\":\"94086\",\"lon\":-12203224,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"141 S TAAFFE ST\",\"type\":0,\"lat\":3737744,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1906\",\"distanceWithUnit\":\"1.2 mi\",\"phoneNumber\":\"4087380257\",\"poiId\":\"3432546798\",\"categoryId\":\"122\"},\"isAdsPoi\":false,\"categoryLogo\":\"/tnimages/logo/cat-post.png\",\"rating\":30,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":2,\"popularity\":0},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94086\",\"lon\":-12203224,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"141 S TAAFFE ST\",\"type\":0,\"lat\":3737744,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":2,\"phoneNumber\":\"4087380257\",\"status\":1,\"label\":\"US Post Office\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String ATM = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94085\",\"lon\":-12199488,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1202 E ARQUES AVE\",\"type\":0,\"lat\":3738044,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":true,\"hasAdsMenu\":false,\"hasPoiDetails\":true,\"hasReviews\":true,\"type\":196,\"isRatingEnable\":true,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"brand\":\"Wells Fargo\",\"stop\":{\"zip\":\"94085\",\"lon\":-12199488,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1202 E ARQUES AVE\",\"type\":0,\"lat\":3738044,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1410\",\"distanceWithUnit\":\"0.9 mi\",\"phoneNumber\":\"4087382114\",\"poiId\":\"3427895699\",\"categoryId\":\"374\"},\"isAdsPoi\":false,\"categoryLogo\":\"/tnimages/logo/cat-bank.png\",\"rating\":35,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":14,\"popularity\":0,\"brandLogo\":\"/tnimages/logo/~Wells-Fargo.png\"},\"existedInFavorite\":false,\"stop\":{\"zip\":\"94085\",\"lon\":-12199488,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1202 E ARQUES AVE\",\"type\":0,\"lat\":3738044,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":4,\"phoneNumber\":\"4087382114\",\"status\":1,\"label\":\"Wells Fargo\",\"type\":2,\"sharedFromPTN\":\"\"}";
	private static String poiDesc = "{\"id\":0,\"poi\":{\"stop\":{\"zip\":\"94085\",\"lon\":-12199792,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1080 STEWART DR\",\"type\":0,\"lat\":3738505,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"hasPoiMenu\":false,\"hasPoiExtraAttributes\":true,\"hasAdsMenu\":false,\"hasPoiDetails\":true,\"hasReviews\":true,\"type\":196,\"isRatingEnable\":true,\"hasDeals\":false,\"isSponsorPoi\":false,\"bizPoi\":{\"brand\":\"Residence Inn Sunnyvale Silicon Valley II\",\"stop\":{\"zip\":\"94085\",\"lon\":-12199792,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1080 STEWART DR\",\"type\":0,\"lat\":3738505,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"distance\":\"1321\",\"distanceWithUnit\":\"0.8 mi\",\"phoneNumber\":\"4087208893\",\"poiId\":\"3000538456\",\"categoryId\":\"595\"},\"isAdsPoi\":false,\"categoryLogo\":\"/tnimages/logo/cat-accomodation.png\",\"rating\":38,\"rated\":false,\"usePreviousRating\":0,\"rateNumber\":16,\"popularity\":0},\"existedInFavorite\":true,\"stop\":{\"zip\":\"94085\",\"lon\":-12199792,\"isGeocoded\":false,\"stopId\":0,\"province\":\"CA\",\"label\":\"\",\"firstLine\":\"1080 STEWART DR\",\"type\":0,\"lat\":3738505,\"city\":\"SUNNYVALE\",\"country\":\"US\"},\"sharedFromUser\":\"\",\"selectedIndex\":1,\"phoneNumber\":\"4087208893\",\"status\":1,\"label\":\"Residence Inn Sunnyvale Silicon Valley II\",\"type\":2,\"sharedFromPTN\":\"\"}";

	@Override
	/**
	 * 1) parse paramter from httprequest
	 * 2) run logic in execute
	 * 3) format httprequest to front end
	 */
	protected ActionForward doAction(ActionMapping mapping,HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result=defaultData;
		String poi = request.getParameter("poi");
		if(poi == null || poi.equals("")){
			result = defaultData;
		}else if(poi.equalsIgnoreCase("default")){
			result = defaultData;
		}else if(poi.equalsIgnoreCase("gas")){
			result = gas;
		}else if(poi.equalsIgnoreCase("menuExtra")){
			result = menuExtra;
		}else if(poi.equalsIgnoreCase("sponsor")){
			result = sponsor;
		}else if(poi.equalsIgnoreCase("showtime")){
			result = showtime;
		}else if(poi.equalsIgnoreCase("ads")){
			result = ads;
		}else if(poi.equalsIgnoreCase("sponsorWithAD")){
			result = sponsorWithAD;
		}else if(poi.equalsIgnoreCase("new4IPhone")){
			result = new4IPhone;
		}else if(poi.equalsIgnoreCase("hotel")){
			result = hotel;
		}else if(poi.equalsIgnoreCase("openTable")){
			result = openTable;
		}else if(poi.equalsIgnoreCase("postOffice")){
			result = postOffice;
		}else if(poi.equalsIgnoreCase("ATM")){
			result = ATM;
		}else if(poi.equalsIgnoreCase("poiDesc")){
			result = poiDesc;
		}else if(poi.equalsIgnoreCase("custom")){
			result = (String)request.getSession().getAttribute("CUSTOMJSON");
			request.getSession().removeAttribute("CUSTOMJSON");
		}
		request.setAttribute("ajaxResponse", result);
		return mapping.findForward("success");
	}
}