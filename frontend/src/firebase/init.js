import {initializeApp} from "firebase/app"
// import * as firebase from "firebase"
import {getStorage} from "firebase/storage";

const firebaseConfig = {
    apiKey: "AIzaSyCUh5aCXGoArH9y8WUTCrIdhROHLb3ekf0",
    authDomain: "hotelbooking-f2105.firebaseapp.com",
    projectId: "hotelbooking-f2105",
    storageBucket: "hotelbooking-f2105.appspot.com",
    messagingSenderId: "27097231607",
    appId: "1:27097231607:web:250b89b3913eea73dbe3f2",
    measurementId: "G-78CTTY0SVY"
};
export const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);