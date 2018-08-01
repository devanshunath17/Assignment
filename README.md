# Assignment
This is a demo project to demonstrate the Recycler View  using Retrofit and to show how to use  Android Architecture component Room  to access SQLite database on device for reading and writing data. This example also shows how to use LiveData and ViewModel with Room to build reactive, well performing and easy to maintain applications.

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Dagger2.
3. **ui**: View classes along with their corresponding ViewModel.
4. **utils**: Utility classes.

### Library reference resources:
1. RxJava2: https://github.com/amitshekhariitbhu/RxJava2-Android-Samples
2. Dagger2: https://github.com/MindorksOpenSource/android-dagger2-example
3. FastAndroidNetworking: https://github.com/amitshekhariitbhu/Fast-Android-Networking
4. PlaceHolderView: https://github.com/janishar/PlaceHolderView
5. AndroidDebugDatabase: https://github.com/amitshekhariitbhu/Android-Debug-Database
6. Calligraphy: https://github.com/chrisjenx/Calligraphy
7. Room: https://developer.android.com/topic/libraries/architecture/room.html

# Model View Presenter
The MVP Designe pattern allows separate the presentation layer from the logic , so that everything about how the interface works is separated from how we represent it on screen. Ideally the MVP pattern would achieve that same logic might have completely different and interchangeable views.
# About
In this Demo, i am using :
# 1.Retrofit Library :-    
compile 'com.squareup.retrofit2:converter-gson:2.1.0'
# 2 RecyclerView ,Cardview  :-   
compile 'com.android.support:recyclerview-v7:26.1.0', compile 'com.android.support:cardview-v7:26.1.0'
# 3.Glide  for displaying the image. :-  
compile 'com.github.bumptech.glide:glide:3.7.0'
# 4.Room Persistence Library  :-   
implementation "android.arch.persistence.room:runtime:1.0.0",annotationProcessor "android.arch.persistence.room:compiler:1.0.0"
# 5.JobScheduler :- 
compile 'com.birbit:android-priority-jobqueue:2.0.0-beta1'
# 6.Event :-   
compile 'org.greenrobot:eventbus:3.0.0'


