echo '最近计划逐一调试google有用的samples'
echo '20170428 整理出的需要研究的资源！'
echo 'reference : https://developer.android.com/reference/classes.html'

echo '以下最低要求android21-5.0才能使用，因为camera2是它下边才有的。    以下所有实例，在sdk的samples里都有。可不用从github上下载。'


git clone --depth 1 https://github.com/googlesamples/android-ConstraintLayoutExamples.git               # -19   ConstrainLayout示例，该优先研究
git clone --depth 1 https://github.com/googlesamples/android-MediaRecorder.git               # -16 > 14     标准摄像头录制 mp4
git clone --depth 1 https://github.com/googlesamples/android-BasicMediaDecoder.git            #17       MediaCodec 用Wrapper方式操作原素材
git clone --depth 1 https://github.com/googlesamples/android-Camera2Basic.git               # -21         使用CameraManager获取摄像头并展示
git clone --depth 1 https://github.com/googlesamples/android-Camera2Raw.git               # -21             使用CameraDevice 获取照片
git clone --depth 1 https://github.com/googlesamples/android-Camera2Video.git               # -21         使用CameraManager获取摄像头并拍摄视频


git clone --depth 1 https://github.com/googlesamples/android-BasicRenderScript.git               # -16 > 14 RenderScript 对bitmap进行饱和度调节
git clone --depth 1 https://github.com/googlesamples/android-MediaEffects.git               # -14      openGL 渲染图片
git clone --depth 1 https://github.com/googlesamples/android-BasicTransition.git               # -19     TransitionManager进行Scene多场景动画转换
git clone --depth 1 https://github.com/googlesamples/android-BatchStepSensor.git               # -19        逐步操作功能，同一页显示，动画效果
git clone --depth 1 https://github.com/googlesamples/android-AccelerometerPlay.git               # -11    重力感应的重力加速画小球


echo '以下待编译，看是否需要研究-20170502！！！'
git clone --depth 1 https://github.com/googlesamples/android-RecyclerView.git               # -7   Support v7 RecyclerView 
git clone --depth 1 https://github.com/googlesamples/android-MediaRouter.git               # -13   选择路由。
git clone --depth 1 https://github.com/googlesamples/android-PictureInPicture.git               # -O 26 新API
git clone --depth 1 https://github.com/googlesamples/android-UniversalMusicPlayer.git               # -17
git clone --depth 1 https://github.com/googlesamples/android-testing-templates.git               # -0  有关测试的模板
git clone --depth 1 https://github.com/googlesamples/android-vision.git               # -9
git clone --depth 1 https://github.com/googlesamples/apps-script-mobile-addons.git               # -17
git clone --depth 1 https://github.com/googlesamples/easypermissions.git               # -9
git clone --depth 1 https://github.com/googlesamples/android-RevealEffectBasic.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-ScreenCapture.git               # -21


echo '以下正常都可以运行'
git clone --depth 1 https://github.com/googlesamples/android-ActionBarCompat-ListPopupMenu.git               # -7  
git clone --depth 1 https://github.com/googlesamples/android-ActionBarCompat-ShareActionProvider.git               # -7
git clone --depth 1 https://github.com/googlesamples/android-ActionBarCompat-Styled.git               # -7
git clone --depth 1 https://github.com/googlesamples/android-BasicGestureDetect.git               # -18     
git clone --depth 1 https://github.com/googlesamples/android-BasicSyncAdapter.git               # -7
git clone --depth 1 https://github.com/googlesamples/android-BorderlessButtons.git               # -14
git clone --depth 1 https://github.com/googlesamples/android-CardView.git               # -7   
git clone --depth 1 https://github.com/googlesamples/android-CustomChoiceList.git               # -7     列表行选中效果
git clone --depth 1 https://github.com/googlesamples/android-CustomTransition.git               # -19      切换场景动画，多Scene间
git clone --depth 1 https://github.com/googlesamples/android-DisplayingBitmaps.git               # -9        写的ImageCache缓存Bitmap用于展示
git clone --depth 1 https://github.com/googlesamples/android-DoneBar.git               # -9                没意思，头部显示actionBar 或 按钮
git clone --depth 1 https://github.com/googlesamples/android-HorizontalPaging.git               # -11
git clone --depth 1 https://github.com/googlesamples/android-ImmersiveMode.git               # -11
git clone --depth 1 https://github.com/googlesamples/android-RecipeAssistant.git               # -18
git clone --depth 1 https://github.com/googlesamples/android-RenderScriptIntrinsic.git               # -13
git clone --depth 1 https://github.com/googlesamples/android-SlidingTabsBasic.git               # -14
git clone --depth 1 https://github.com/googlesamples/android-StorageProvider.git               # -19
git clone --depth 1 https://github.com/googlesamples/android-SwipeRefreshLayoutBasic.git               # -14
git clone --depth 1 https://github.com/googlesamples/android-SwipeRefreshListFragment.git               # -14
git clone --depth 1 https://github.com/googlesamples/android-SwipeRefreshMultipleViews.git               # -9
git clone --depth 1 https://github.com/googlesamples/android-TextLinkify.git               # -7
git clone --depth 1 https://github.com/googlesamples/android-TextSwitcher.git               # -7
git clone --depth 1 https://github.com/googlesamples/android-WeaveLedToggler.git               # -16
git clone --depth 1 https://github.com/googlesamples/android-fit.git               # -9
git clone --depth 1 https://github.com/googlesamples/android-google-accounts.git               # -9
git clone --depth 1 https://github.com/googlesamples/android-nearby.git               # -9-19
git clone --depth 1 https://github.com/googlesamples/android-play-places.git               # -14
git clone --depth 1 https://github.com/googlesamples/android-topeka.git               # -14
git clone --depth 1 https://github.com/googlesamples/easygoogle.git               # -15


rem  ├── android-21

git clone --depth 1 https://github.com/googlesamples/android-ActivitySceneTransitionBasic.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-AppShortcuts.git               # -25       类iphone6重按显示子选项，打开网页快捷方式。
git clone --depth 1 https://github.com/googlesamples/android-ClippingBasic.git               # -21        裁切效果，类切换ViewAnimator，实现View的切圆角类效果
git clone --depth 1 https://github.com/googlesamples/android-ConfirmCredential.git               # -23
git clone --depth 1 https://github.com/googlesamples/android-DirectBoot.git               # -24
git clone --depth 1 https://github.com/googlesamples/android-DirectShare.git               # -23
git clone --depth 1 https://github.com/googlesamples/android-DirectorySelection.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-DrawableTinting.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-ElevationDrag.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-FloatingActionButtonBasic.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-HdrViewfinder.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-Interpolator.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-JobScheduler.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-NavigationDrawer.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-OurStreets.git               # -21
git clone --depth 1 https://github.com/googlesamples/android-unsplash.git               # -21




git clone --depth 1 https://github.com/googlesamples/android-audio-high-performance.git               # -25 X    代码不通，应该是音频高性能优化。

echo 'done! 68 entries need to build and debug!'
