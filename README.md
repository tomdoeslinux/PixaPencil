<img src="https://i.imgur.com/Aq8kgtZ.png"/>

**A pixel art creator for Android using RecyclerView.**

----
![Fri Oct 22 2021 10_20_15 PM](https://user-images.githubusercontent.com/50536495/138430387-76f56fbe-14de-4624-b330-cb9eab59eac7.gif)
## Showcasing the app's capabilities
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
class Dog : Animal {

}
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
