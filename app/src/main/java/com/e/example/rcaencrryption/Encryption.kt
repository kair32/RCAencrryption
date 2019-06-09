package com.e.example.rcaencrryption

import android.util.Base64
import android.util.Log
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.*
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

class Encryption(){
    val TAG = "TAG APPLICATION"
    private fun encryption(){
        val str = "Hello world"
        val byte = str.toByteArray()


        val key = Base64.decode(keyGeneratorAES("WWWWWWWWWW"), Base64.NO_WRAP)
        val secret = SecretKeySpec(key, "AES")
        val sd = encryptAES(str, key,secret)

        Log.d(TAG," Строка= " + sd)
        val des = decryptAES(sd,key,secret)
        Log.d(TAG," Строка= " + des)
    }
    fun keyGeneratorAES(pKey: String): String {
        var key = ""
        try {
            val salt = byteArrayOf(-84, -119, 25, 56, -100, 100, -120, -45, 84, 67, 96, 10, 24, 111, 112, -119, 3)
            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            val spec = PBEKeySpec(pKey.toCharArray(), salt, 1024, 128)
            val tmp = factory.generateSecret(spec)
            var secret = SecretKeySpec(tmp.encoded, "AES")
            key = Base64.encodeToString(secret.encoded, Base64.NO_WRAP)
        } catch (ex: Exception) {
            ex.printStackTrace()
            key = ""
        }
        return key
    }
    @Throws(
        NoSuchPaddingException::class, NoSuchAlgorithmException::class, InvalidAlgorithmParameterException::class,
        BadPaddingException::class, InvalidKeyException::class, ShortBufferException::class,
        IllegalBlockSizeException::class)
    private fun encryptAES(cleartext: String, iv: ByteArray, secret: SecretKeySpec): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secret, IvParameterSpec(iv))
        val ciphertext = cipher.doFinal(cleartext.toByteArray(charset("UTF-8")))
        Log.d(TAG," Строка ====== " + String(iv))
        return Base64.encodeToString(ciphertext, Base64.NO_WRAP)
    }
    private fun decryptAES(encrypted: String, iv: ByteArray?, secret: SecretKeySpec): String {
        try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, secret, if (iv == null) null else IvParameterSpec(iv))
            var original = cipher.doFinal(Base64.decode(encrypted, Base64.NO_WRAP))
            return String(original)
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: InvalidAlgorithmParameterException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: ShortBufferException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }
        return ""
    }


    private fun decryption(){
        val byte = "sfds".toByteArray()
        val strpost: String = String(byte)
        Log.d(TAG," Строка после= " + strpost)
    }

    private fun generationKey() {
        var p = 1
        var q = 1
        while (!checkSimple(p))
            p = (2..10000).random()
        while (!checkSimple(q))
            q = (2..10000).random()
        p = 29
        q = 31
        val n = p*q
        val fiN = (p-1)*(q-1)
        val e = createE(fiN)
        Log.d(TAG, "my Message " + p + " == " + q)
        //создали открытый ключ
        val d = closeKey(fiN.toDouble(),e.toDouble())


        Log.d(TAG, " Открытый ключ = " + n + "," + e)//отправляем клиенту
        Log.d(TAG, " Закрытый ключ = " + n + "," + d)//Храним в тайне от всех
    }
    private fun closeKey(fiN: Double, e: Double):Int{
        var k = 1.0
        var d: Double
        do {
            d = ((k*fiN)+1)/e
            k +=1
        }
        while (d % 1 != 0.0000 )
        return d.toInt()
    }
    private fun createE(fiN: Int):Int{
        var e = (2..10000).random()
        if (e % 2 == 0) e++
        e = 11
        Log.d(TAG," Ищем открытую экспоненту из = " + e +" \nfiN= " + fiN)
        for (i in 2..fiN){
            if(e%i==0 && fiN%i==0)
                Log.d(TAG," ОТкрытая экспонента не подходит" + e +" \nобщий делитель " + i + " \nfiN= " + fiN)
        }
        return e;
    }
    private fun checkSimple(i: Int): Boolean {
        if (i <= 1)
            return false
        else if (i <= 3)
            return true
        else if (i % 2 == 0 || i % 3 == 0)
            return false
        var n = 5
        while (n * n <= i) {
            if (i % n == 0 || i % (n + 2) == 0)
                return false
            n += 6
        }
        return true
    }
}