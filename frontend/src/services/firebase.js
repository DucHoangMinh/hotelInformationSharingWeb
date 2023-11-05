import {uploadBytes, ref, getDownloadURL} from "firebase/storage";
import {storage} from "@/firebase/init"
const firebaseService = {
    upload: async (file) => {
        const storageRef = ref(storage, file.name + new Date().toString());
        await uploadBytes(storageRef, file);
        const data = getDownloadURL(storageRef)
        return data;
    }
}
export default firebaseService