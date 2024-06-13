<template>
    <div>
        <img id="rndmImage" :src="randomImage" alt="Random Image" />
    </div>

</template>

<script>
import image1 from '../RandomImages/sausageballs.webp';
import image2 from '../RandomImages/salad.webp';
import image3 from '../RandomImages/pexels-monicore-1391487.jpg';
import image4 from '../RandomImages/pexels-goumbik-616484.jpg';
import image5 from '../RandomImages/omelet.jpg';
import image6 from '../RandomImages/oatmeal2-1-of-1.webp';
import image7 from '../RandomImages/moussemiddle-1-of-1.webp';
import image8 from '../RandomImages/moscowmules.jpg';
import image9 from '../RandomImages/img_4961-copy.webp';
import image10 from '../RandomImages/Chicken-Parmesan-1.jpg';


export default {
    data() {
        return {
            images: [image1, image2, image3, image4, image5, image6, image7, image8, image9, image10],
            randomImage: ''
        };
    },

    created() {
        this.randomImage = this.getRandomImage();
    },

    methods: {
        getRandomImage() {
            /*images.sort(() => 0.5 - Math.random()) to prevent duplicates? */
            const randomIndex = Math.floor(Math.random() * this.images.length);
            return this.images[randomIndex];
        }
    }

};
</script>

<style>



</style>