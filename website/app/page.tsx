import { Image } from '@chakra-ui/next-js'
import PixaPencil_Logo from '../public/pixapencil-logo.jpg'

export default function Home() {
  return (
    <Image src={PixaPencil_Logo} width={40} height={40} alt="App logo" />
  )
}
