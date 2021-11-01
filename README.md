![image](https://user-images.githubusercontent.com/50536495/139613827-1d5ea69b-5ffc-4413-86ae-cba9c4e8160d.png)

**A pixel art creator for Android using RecyclerView.**

_Join our Discord: https://discord.gg/fZPWBfPvWd_

_COMMITS ARE NOT STABLE AND HAVEN'T BEEN CHECKED FOR BUGS._

_IF YOU ARE INTERESTED IN BRANCHING THIS PROJECT PLEASE TAKE THAT FACT INTO CONSIDERATION. IT IS RECOMMENDED THAT YOU BRANCH ONLY WHEN RELEASE 0.1 COMES OUT AND MOST COMMITS AREN'T CHECKED BECAUSE OF THE LACK OF UNIT TESTS!_

----
## Showcasing the app's capabilities
![image](https://user-images.githubusercontent.com/50536495/139620161-c0ef2fc9-12e5-4404-a269-c9e7023a6e87.png)

![drawing](https://user-images.githubusercontent.com/50536495/138544026-4bd15055-3794-4786-a758-9c2f2633381f.png)
![Screenshot 2021-10-24 110235](https://user-images.githubusercontent.com/50536495/138572942-c017eb05-85a4-4159-a351-3204e5d422aa.png)


## Contributing Guidelines
I would love your input! I have a goal of making contributing to this project as easy and transparent as possible, whether it's:

- _Reporting a bug_
- _Discussing the current state of the code_
- _Submitting a fix_
- _Proposing new features_
- _Becoming a maintainer_

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

## User Inteface Evolution

_PyxlMoose has made a tremendous amount of progress!_

#### Then:

![image](https://user-images.githubusercontent.com/50536495/139565804-fe80c56c-7d92-46b0-bd24-9d7f328962f0.png)
![image](https://user-images.githubusercontent.com/50536495/139565798-65f42d72-81a7-4a2e-ae5e-0816fa07d0db.png)

#### Now:
![image](https://user-images.githubusercontent.com/50536495/139565858-d55ce8d8-d9e6-4c2e-adae-451cd73fcb91.png)
![image](https://user-images.githubusercontent.com/50536495/139565849-17e280c6-65ef-40c0-b693-67b5b9e906db.png)


## Use a Consistent Coding Style
<details><summary><b>Examples</b></summary>

#### Functions:
``` Kotlin
fun myFunc() {

}
```
---
#### Functions with one line:
``` Kotlin
fun getInt() = 5 
```
---
#### Functions that return something and have more than one line:
``` Kotlin
fun getDog(): Dog {
  return Dog()
}
```
---
#### Variables:
```Kotlin
val x = "Hello"
```
---

#### Parameters:
```Kotlin
fun myFunc(any: Any) {

}
```
---
#### Classes:

```Kotlin
class Dog : Animal 
```
---
#### Constants:
```Kotlin
const val MY_AGE = 15
```
#### Ordering of modifiers (from Kotlin documentation):
---
``` Kotlin
public / protected / private / internal
expect / actual
final / open / abstract / sealed / const
external
override
lateinit
tailrec
vararg
suspend
inner
enum / annotation / fun // as a modifier in `fun interface`
companion
inline / value
infix
operator
data
```

_**Do not** use the PyxlMoose name anywhere else other than this project.😊_
