package com.student.jpush;

import java.util.HashMap;
import java.util.Map;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.device.TagAliasResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.InterfaceAdapter;
import cn.jpush.api.push.model.notification.IosAlert;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import cn.jpush.api.report.MessagesResult.Message;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Demo {
	// 在极光注册上传应用的 appKey 和 masterSecret
	private static final String appKey = "d97fa0dd2bd5f2a2294bb158";// //必填，例如466f7032ac604e02fb7bda89
	private static final String masterSecret = "a1d912a1fb1c336036434024";// 必填，每个应用都对应一个masterSecret
	private static long timeToLive = 60 * 60 * 24;

	public static final String TITLE = "Test from API example";
	public static final String ALERT = "Test from API Example - alert";
	public static final String MSG_CONTENT = "Test from API Example - msgContent";
	public static final String REGISTRATION_ID = "0900e8d85ef";
	public static final String TAG = "tag_api";

	public static void main(String[] args) {

		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		//
		try {
			PushPayload payload = buildPushObject_all_all_alert();
			PushResult result = jpushClient.sendPush(payload);

		} catch (APIConnectionException e) {

		} catch (APIRequestException e) {
		}

	}

	public static PushPayload buildPushObject_all_alias_alert() {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias("15")).setNotification(Notification.alert("Ponymesg"))
				.setMessage(cn.jpush.api.push.model.Message.content("Ponymesg")).build();
	}

	// use String to build PushPayload instance
	public static void testSendPush_fromJSON() {
		ClientConfig clientConfig = ClientConfig.getInstance();
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
		Gson gson = new GsonBuilder().registerTypeAdapter(PlatformNotification.class, new InterfaceAdapter<PlatformNotification>()).create();
		// Since the type of DeviceType is enum, thus the value should be
		// uppercase, same with the AudienceType.
		String payloadString = "{\"platform\":{\"all\":false,\"deviceTypes\":[\"IOS\"]},\"audience\":{\"all\":false,\"targets\":[{\"audienceType\":\"TAG_AND\",\"values\":[\"tag1\",\"tag_all\"]}]},\"notification\":{\"notifications\":[{\"soundDisabled\":false,\"badgeDisabled\":false,\"sound\":\"happy\",\"badge\":\"5\",\"contentAvailable\":false,\"alert\":\"Test from API Example - alert\",\"extras\":{\"from\":\"JPush\"},\"type\":\"cn.jpush.api.push.model.notification.IosNotification\"}]},\"message\":{\"msgContent\":\"Test from API Example - msgContent\"},\"options\":{\"sendno\":1429488213,\"overrideMsgId\":0,\"timeToLive\":-1,\"apnsProduction\":true,\"bigPushDuration\":0}}";
		PushPayload payload = gson.fromJson(payloadString, PushPayload.class);
		try {
			PushResult result = jpushClient.sendPush(payload);

		} catch (APIConnectionException e) {

		} catch (APIRequestException e) {
		}
	}

	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll(ALERT);
	}

	public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		return PushPayload.newBuilder().setPlatform(Platform.android()).setAudience(Audience.tag("tag1"))
				.setNotification(Notification.android(ALERT, TITLE, null)).build();
	}

	public static PushPayload buildPushObject_android_and_ios() {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.tag("tag1"))
				.setNotification(
						Notification.newBuilder().setAlert("alert content")
								.addPlatformNotification(AndroidNotification.newBuilder().setTitle("Android Title").build())
								.addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtra("extra_key", "extra_value").build())
								.build()).build();
	}

	public static void buildPushObject_with_extra() {

		JsonObject jsonExtra = new JsonObject();
		jsonExtra.addProperty("extra1", 1);
		jsonExtra.addProperty("extra2", false);

		Map<String, String> extras = new HashMap<String, String>();
		extras.put("extra_1", "val1");
		extras.put("extra_2", "val2");

		PushPayload payload = PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.tag("tag1"))
				.setNotification(
						Notification
								.newBuilder()
								.setAlert("alert content")
								.addPlatformNotification(
										AndroidNotification.newBuilder().setTitle("Android Title").addExtras(extras).addExtra("booleanExtra", false)
												.addExtra("numberExtra", 1).addExtra("jsonExtra", jsonExtra).build())
								.addPlatformNotification(IosNotification.newBuilder().incrBadge(1).addExtra("extra_key", "extra_value").build())
								.build()).build();

		System.out.println(payload.toJSON());
	}

	public static void testSendPushWithCustomConfig() {
		ClientConfig config = ClientConfig.getInstance();
		// Setup the custom hostname
		config.setPushHostName("https://api.jpush.cn");

		JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, config);

		// For push, all you need do is to build PushPayload object.
		PushPayload payload = buildPushObject_all_all_alert();

		try {
			PushResult result = jpushClient.sendPush(payload);

		} catch (APIConnectionException e) {

		} catch (APIRequestException e) {
		}
	}

	public static void testSendIosAlert() {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);

		IosAlert alert = IosAlert.newBuilder().setTitleAndBody("test alert", "subtitle", "test ios alert json").setActionLocKey("PLAY").build();
		try {
			PushResult result = jpushClient.sendIosNotificationWithAlias(alert, new HashMap<String, String>(), "alias1");
		} catch (APIConnectionException e) {
		} catch (APIRequestException e) {
		}
	}

	public static void testSendWithSMS() {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		try {
			SMS sms = SMS.content("Test SMS", 10);
			PushResult result = jpushClient.sendAndroidMessageWithAlias("Test SMS", "test sms", sms, "alias1");
		} catch (APIConnectionException e) {
		} catch (APIRequestException e) {
		}
	}

}
