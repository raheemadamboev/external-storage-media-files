# external-storage-media-files

A simple app that demonstrates reading all media files from external storage with handling permissions properly according to Android API level.

[Download demo](https://github.com/raheemadamboev/external-storage-media-files/blob/main/extra/app-debug.apk)

---

`READ_EXTERNAL_STORAGE` permission is requested on devices running API 32 and below (Android 12L). The permissions grants access to all types of media files.

<p align="center">
   <img width="296" height="600" src="https://github.com/raheemadamboev/external-storage-media-files/blob/main/extra/banner_2.gif" />
</p>

---

In API 33 (Android 13) and above, splitting permissions for each media type is introduced: `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_MEDIA_AUDIO`. `READ_EXTERNAL_STORAGE` permission is deprecated and no longer used for these devices.

API 34 (Android 14) extended this permissons further by adding `READ_MEDIA_VISUAL_USER_SELECTED` that gives user ability to choose what image and video files to share with the app.

<p align="center">
   <img width="296" height="600" src="https://github.com/raheemadamboev/external-storage-media-files/blob/main/extra/banner_1.gif" />
</p>
