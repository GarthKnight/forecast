# weather-challenge
Simple forecast application. 
# Architecture
In this application I'm using Clean architecture. In small project probably it seems little bit excess, but in big projects it's super useful. 
Idealy data/domain/common packages would be moved to android modules. 

MVVM seems for me the best pattern because it is super easy to implement and it is helping with managing screen states so much. 
# Build dependencies
I'm using Hilt for DI because it provides containers for every Android class and managing their lifecycles automatically. 
I think it's super convenient, especially in small projects. 

Also I've added Fresco for working with images. I've chosen it because it's simplest solution. 
If there will be some more difficult stuff to do it would be better to use Glide. 

For network I'm using OkHttp/Retrofit because I'm thinking it's the only option when working with open APIs. 

Also I'm using WorkManager for persistent work in background because it is solution recommended by Google and it is matching good with other dependencies, such as hilt.

And for navigation I'm using AndroidX Navigation and safe args because it is easy to implement and to use. Also it is recomended by Google too.
