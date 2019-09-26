"# BasisTask"

BasisTask is an app which fetches data and display in the form of swipable cards.

https://github.com/kiterunner20/BasisTask/blob/master/Screenshot_20190926-183439.jpg

Model-View-Presenter(MVP) is the architecure used for the implementation of project.

MVP Implementation.

To ease out implementation in MVP architecture , some core classes are created in the core package in the UI Module.
This will act as generic classes for the child classes to avoid repeatation of code. This single screen app is arranged in such a way that a call is invoked from the activity from there its called from presenter and then to the model.
Observable streams are recieved and passed back to the presenter then back to the view.

Libraries Used

1.Butterknife
Eliminate findViewById calls by using @BindView on fields.
Eliminate anonymous inner-classes for listeners by annotating methods with @OnClick and others.
Eliminate resource lookups by using resource annotations on fields.

2.Evernote Android state
For maintaining state and keep the state of each of the object or variables which is annotated with @state
Solve the issue of manually maintaing the state via bundle

3.RxJava
RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences. RxJava and RxAndroid are used in the project and take care of the asynchronous network calls.
The concept of multithreading is also accomplished easily by rxJava keeping the network and UI in the separate threads.
A few concepts used are Observables, subscription and threading separate and also calls unsubsribe when view is detached.
Map operator is used for mapping the serializable class to a parcelable class.

4 Retrofit and OkHTTP
Retrofit is a type-safe REST adapter that makes easy common networking tasks. Retrofit library takes care of the networking side and with rxJavaConverterAdapterFactory retrofit returns the observable seequence.Retrofit wrapper is implemented on top of the OkHttp for the smooth http call.Custom GsonConverterFactory is created to removed the / in the json.

5. Dependency Injection- Dagger2
DI is done via it takes cares of the Dependency injection . A fewb annotations like @Provide , @Module etc will do the annotation processesing and helps the DI pretty easier.

6. Support libraries
Project is in AndroidX and used recyclerView , cardView and meterial design

7. MultiviewAdapter
Helps in easy creation of multiple view types with less boilerplate code. The capability of this library is not used much in this project.


How to build?

gradlew assembleRelease





