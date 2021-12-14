![image](https://user-images.githubusercontent.com/50536495/139613827-1d5ea69b-5ffc-4413-86ae-cba9c4e8160d.png)

**A pixel art creator for Android using Canvas, written 100% in Kotlin.**

_Join our Discord: https://discord.gg/fZPWBfPvWd_

This project is **closed** for contributions as of the 15th of December 2021.

----
## Showcasing the app's capabilities
![image](https://user-images.githubusercontent.com/50536495/139620161-c0ef2fc9-12e5-4404-a269-c9e7023a6e87.png)
![image](https://user-images.githubusercontent.com/50536495/139562893-ea67558d-24c3-416b-a802-8898805d5514.png)

## Contributing Guidelines
I would love your input! I have a goal of making contributing to this project as easy and transparent as possible, whether it's:

- _Reporting a bug_
- _Discussing the current state of the code_
- _Submitting a fix_
- _Proposing new features_
- _Becoming a maintainer_

Make sure to follow clean code guidelines when contributing to this project.

## Want to collaborate?
If you want to collaborate with me on the project flick me an email at tomdoescodeallday@gmail.com.

## Features
#### Mirror tool:
![Screen Recording - Made with RecordCast](https://user-images.githubusercontent.com/50536495/139818972-d6daea00-5925-4f48-a17b-168ed8520306.gif)

#### Lighten tool:
![Screen Recording - Made with RecordCast (1)](https://user-images.githubusercontent.com/50536495/139819623-fe66a42d-3b6a-4180-b335-4c1b6ddc1c05.gif)

#### Darken tool:
![Screen Recording - Made with RecordCast (2)](https://user-images.githubusercontent.com/50536495/139819953-c84c6f89-a3d0-4958-a82f-10edfd499f0a.gif)

#### Color picker tool:
![Screen Recording - Made with RecordCast (3)](https://user-images.githubusercontent.com/50536495/139820352-c6e194b6-1df1-4fbb-94e6-636e006587ff.gif)

#### Use any color you want to create beautiful art (hopefully better than mine):
![Screen Recording - Made with RecordCast (4)](https://user-images.githubusercontent.com/50536495/139821358-0f38e9a9-fd35-40f0-bd23-1b6e2435511d.gif)

## User interface evolution

_PyxlMoose has made a tremendous amount of progress!_

#### Then:

![image](https://user-images.githubusercontent.com/50536495/139565804-fe80c56c-7d92-46b0-bd24-9d7f328962f0.png)
![image](https://user-images.githubusercontent.com/50536495/139565798-65f42d72-81a7-4a2e-ae5e-0816fa07d0db.png)

#### Now:
![image](https://user-images.githubusercontent.com/50536495/139565858-d55ce8d8-d9e6-4c2e-adae-451cd73fcb91.png)
![image](https://user-images.githubusercontent.com/50536495/139565849-17e280c6-65ef-40c0-b693-67b5b9e906db.png)


## Shoutout
_Thank you to PapaBread#3820 on Discord for helping me out with the mathematics required to add a mirror tool into the app._

Because I want the code from this project to be accessible and open, I will show you guys the methodology used to mirror a pixel, I thought it would also be quite interesting to share:

``` Kotlin
override fun onPixelTapped(pixel: View) {
        val indexOfIt = data.indexOf(pixel)
        val pos = indexOfIt % spanCount
        if (isMirrorMode) {
            data[(indexOfIt - pos) + (spanCount - pos) - 1].setBackgroundColor(
                getSelectedColour()
            ) // Credits to PapaBread for this masterpiece of a solution
        }


        pixel.setBackgroundColor(getSelectedColour())
    }
```

_Also thank you JohnWick_007#8119 on Discord for providing me some icons to use in my app._
