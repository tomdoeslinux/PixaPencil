![image](https://user-images.githubusercontent.com/50536495/139613827-1d5ea69b-5ffc-4413-86ae-cba9c4e8160d.png)

**A pixel art creator for Android using RecyclerView.**

_Join our Discord: https://discord.gg/fZPWBfPvWd_

_Commits may contain bugs._

_If you are interested in contributing to this project please take that fact into consideration._

_Do not use the PyxlMoose name anywhere else. The first two preview images (and the logo) are originally made by myself so please do not claim them as your own. 😊_

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

## User interface evolution

_PyxlMoose has made a tremendous amount of progress!_

#### Then:

![image](https://user-images.githubusercontent.com/50536495/139565804-fe80c56c-7d92-46b0-bd24-9d7f328962f0.png)
![image](https://user-images.githubusercontent.com/50536495/139565798-65f42d72-81a7-4a2e-ae5e-0816fa07d0db.png)

#### Now:
![image](https://user-images.githubusercontent.com/50536495/139565858-d55ce8d8-d9e6-4c2e-adae-451cd73fcb91.png)
![image](https://user-images.githubusercontent.com/50536495/139565849-17e280c6-65ef-40c0-b693-67b5b9e906db.png)
