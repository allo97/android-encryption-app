package com.example.encryptionapp;

import com.example.encryptionapp.services.AESService;
import com.example.encryptionapp.services.DataService;
import com.example.encryptionapp.services.RSAService;

import org.junit.Test;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class EncryptionChartsTests {
    @Test
    public void testModesEncryptionSpeed()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException {

        String input = "Test encryption speed";
        int counter = 11;
        int meanCounter = 11;
        SecretKey key = AESService.generateKey(256);
        IvParameterSpec ivParameterSpec = AESService.generateIv();

        for (String aesAlgorithm : DataService.AES_ALGORITHMS) {
            var encryptionFilename = "files/testModesEncryptionSpeed/aesEncryptReverse_" + aesAlgorithm.substring(4, 7);
            var encryptionData = new long[counter];
            var encryptionMeanData = new long[meanCounter];

            for (int i = 0; i < meanCounter; i++) {
                for (int j = 0; j < counter; j++) {
                    long startTime = System.nanoTime();
                    String cipherText = aesAlgorithm.equals("AES/ECB/PKCS5Padding") ? AESService.encrypt(aesAlgorithm, input, key) : AESService.encrypt(aesAlgorithm, input, key, ivParameterSpec);
                    long endTime = System.nanoTime();
                    long encryptionDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

                    if (encryptionDuration > 200)
                        encryptionDuration = 200;

                    encryptionData[j] = encryptionDuration;
                }
                encryptionMeanData[i] = (long) DataService.mean(encryptionData);
            }
            DataService.writer(encryptionMeanData, encryptionFilename);
        }
    }

    @Test
    public void testModeEncryptionSpeed()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException {

        String input = "Test encryption speed";
        String aesAlgorithm = DataService.AES_ALGORITHMS[4];
        int counter = 11;
        int meanCounter = 11;
        SecretKey key = AESService.generateKey(256);
        IvParameterSpec ivParameterSpec = AESService.generateIv();

        var encryptionFilename = "files/testModesEncryptionSpeed/aesEncrypt_" + aesAlgorithm.substring(4, 7);
        var encryptionData = new long[counter];
        var encryptionMeanData = new long[meanCounter];

        for (int i = 0; i < meanCounter; i++) {
            for (int j = 0; j < counter; j++) {
                long startTime = System.nanoTime();
                String cipherText = aesAlgorithm.equals("AES/ECB/PKCS5Padding") ? AESService.encrypt(aesAlgorithm, input, key) : AESService.encrypt(aesAlgorithm, input, key, ivParameterSpec);
                long endTime = System.nanoTime();
                long encryptionDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

                if (encryptionDuration > 200)
                    encryptionDuration = 200;

                encryptionData[j] = encryptionDuration;
            }
            encryptionMeanData[i] = (long) DataService.mean(encryptionData);
        }
        DataService.writer(encryptionMeanData, encryptionFilename);
    }

    @Test
    public void testModesDecryptionSpeed()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException {

        String input = "Test encryption speed";
        int counter = 11;
        int meanCounter = 11;
        SecretKey key = AESService.generateKey(256);
        IvParameterSpec ivParameterSpec = AESService.generateIv();

        for (String aesAlgorithm : DataService.AES_ALGORITHMS) {
            String cipherText = aesAlgorithm.equals("AES/ECB/PKCS5Padding") ? AESService.encrypt(aesAlgorithm, input, key) : AESService.encrypt(aesAlgorithm, input, key, ivParameterSpec);

            var decryptionFilename = "files/testModesEncryptionSpeed/aesDecrypt_" + aesAlgorithm.substring(4, 7);
            var decryptionData = new long[counter];
            var decryptionMeanData = new long[meanCounter];

            for (int i = 0; i < meanCounter; i++) {
                for (int j = 0; j < counter; j++) {
                    long startTime = System.nanoTime();
                    String plainText = aesAlgorithm.equals("AES/ECB/PKCS5Padding") ? AESService.decrypt(aesAlgorithm, cipherText, key) : AESService.decrypt(aesAlgorithm, cipherText, key, ivParameterSpec);
                    long endTime = System.nanoTime();
                    long decryptionDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

                    if (decryptionDuration > 200)
                        decryptionDuration = 200;

                    decryptionData[j] = decryptionDuration;
                }
                decryptionMeanData[i] = (long) DataService.mean(decryptionData);
            }
            DataService.writer(decryptionMeanData, decryptionFilename);
        }
    }

    @Test
    public void testModeDecryptionSpeed()
            throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException, IOException {

        String input = "Test encryption speed";
        String aesAlgorithm = DataService.AES_ALGORITHMS[4];
        int counter = 11;
        int meanCounter = 11;
        SecretKey key = AESService.generateKey(256);
        IvParameterSpec ivParameterSpec = AESService.generateIv();

        String cipherText = aesAlgorithm.equals("AES/ECB/PKCS5Padding") ? AESService.encrypt(aesAlgorithm, input, key) : AESService.encrypt(aesAlgorithm, input, key, ivParameterSpec);

        var decryptionFilename = "files/testModesEncryptionSpeed/aesDecrypt_" + aesAlgorithm.substring(4, 7);
        var decryptionData = new long[counter];
        var decryptionMeanData = new long[meanCounter];

        for (int i = 0; i < meanCounter; i++) {
            for (int j = 0; j < counter; j++) {
                long startTime = System.nanoTime();
                String plainText = aesAlgorithm.equals("AES/ECB/PKCS5Padding") ? AESService.decrypt(aesAlgorithm, cipherText, key) : AESService.decrypt(aesAlgorithm, cipherText, key, ivParameterSpec);
                long endTime = System.nanoTime();
                long decryptionDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

                if (decryptionDuration > 200)
                    decryptionDuration = 200;

                decryptionData[j] = decryptionDuration;
            }
            decryptionMeanData[i] = (long) DataService.mean(decryptionData);
        }
        DataService.writer(decryptionMeanData, decryptionFilename);

    }

    @Test
    public void testRSAEncryptionSpeed() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        String input = "Test encryption speed";
        KeyPair keyPair = RSAService.generateRSAKeyPair();
        int counter = 11;
        int meanCounter = 11;
        String encryptedInput = "";

        var encryptionFilename = "files/testRSAEncryptionSpeed/rsaEncrypt";
        var encryptionData = new long[counter];
        var encryptionMeanData = new long[meanCounter];
        for (int j = 0; j < meanCounter; j++) {
            for (int i = 0; i < counter; i++) {
                long startTime = System.nanoTime();
                encryptedInput = RSAService.encrypt(input, keyPair.getPublic());
                long endTime = System.nanoTime();
                long encryptionDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

                encryptionData[i] = encryptionDuration;
            }
            encryptionMeanData[j] = (long) DataService.mean(encryptionData);
        }
        DataService.writer(encryptionMeanData, encryptionFilename);
    }

    @Test
    public void testRSADecryptionSpeed() throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        String input = "Test encryption speed";
        KeyPair keyPair = RSAService.generateRSAKeyPair();
        int counter = 11;
        int meanCounter = 11;
        String encryptedInput = RSAService.encrypt(input, keyPair.getPublic());

        var decryptionFilename = "files/testRSAEncryptionSpeed/rsaDecrypt";
        var decryptionData = new long[counter];
        var decryptionMeanData = new long[meanCounter];
        for (int j = 0; j < meanCounter; j++) {
            for (int i = 0; i < counter; i++) {
                long startTime = System.nanoTime();
                RSAService.decrypt(encryptedInput, keyPair.getPrivate());
                long endTime = System.nanoTime();
                long decryptionDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

                decryptionData[i] = decryptionDuration;
            }
            decryptionMeanData[j] = (long) DataService.mean(decryptionData);
        }
        DataService.writer(decryptionMeanData, decryptionFilename);
    }

    @Test
    public void testAESKeyGeneration() throws NoSuchAlgorithmException, IOException {
        var counter = 11;
        int meanCounter = 11;
        int keyLength = DataService.AES_KEYS_LENGTH[2];

        var filename = "files/testAESKeyGeneration/aesKeyGenerationData_" + keyLength;
        var keyGenData = new long[counter];
        var keyGenMeanData = new long[meanCounter];

        for (int i = 0; i < meanCounter; i++) {
            for (int j = 0; j < counter; j++) {
                long startTime = System.nanoTime();
                SecretKey key = AESService.generateKey(keyLength);
                long endTime = System.nanoTime();
                long keyGenerationDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

                keyGenData[j] = keyGenerationDuration;
            }
            keyGenMeanData[i] = (long) DataService.mean(keyGenData);
        }
        DataService.writer(keyGenMeanData, filename);
    }


    @Test
    public void testRSAKeyGenerationSpeed() throws NoSuchAlgorithmException, IOException {
        int counter = 11;
        int meanCounter = 11;
        var keyGenData = new long[counter];
        var filename = "files/testRSAKeyGeneration/rsaKeyGenerationData";
        var keyGenMeanData = new long[meanCounter];

        for (int j = 0; j < meanCounter; j++) {
            for (int i = 0; i < counter; i++) {
                long startTime = System.nanoTime();
                RSAService.generateRSAKeyPair();
                long endTime = System.nanoTime();
                long keyGenDuration = (endTime - startTime) / 1000000;  //divide by 1000000 to get milliseconds.

                keyGenData[i] = keyGenDuration;
            }
            keyGenMeanData[j] = (long) DataService.mean(keyGenData);
        }
        DataService.writer(keyGenMeanData, filename);
    }

    @Test
    public void testEncryptionWithDifferentKeyLength() throws NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        var counter = 101;

        String input = "Test encryption speed";
        IvParameterSpec ivParameterSpec = AESService.generateIv();
        int keyLength = DataService.AES_KEYS_LENGTH[0];
        SecretKey key = AESService.generateKey(keyLength);

        String filename = "files/encryptionWithDifferentKeyLength/aesEncryptDifferentKey_" + keyLength;
        var data = new long[counter];

        for (int j = 0; j < counter; j++) {
            long startTime = System.nanoTime();
            String cipherText = AESService.encrypt("AES/CBC/PKCS5Padding", input, key, ivParameterSpec);
            long endTime = System.nanoTime();
            long keyGenerationDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

            if (keyGenerationDuration > 300)
                keyGenerationDuration = 300;

            data[j] = keyGenerationDuration;
        }
        DataService.writer(data, filename);
    }

    @Test
    public void testDecryptionWithDifferentKeyLength() throws NoSuchAlgorithmException, IOException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        var counter = 101;

        String input = "Test encryption speed";
        IvParameterSpec ivParameterSpec = AESService.generateIv();
        int keyLength = DataService.AES_KEYS_LENGTH[0];
        SecretKey key = AESService.generateKey(keyLength);
        String cipherText = AESService.encrypt("AES/CBC/PKCS5Padding", input, key, ivParameterSpec);

        String filename = "files/encryptionWithDifferentKeyLength/aesDecryptDifferentKey_" + keyLength;
        var data = new long[counter];

        for (int j = 0; j < counter; j++) {
            long startTime = System.nanoTime();
            AESService.decrypt("AES/CBC/PKCS5Padding", cipherText, key, ivParameterSpec);
            long endTime = System.nanoTime();
            long keyGenerationDuration = (endTime - startTime) / 1000;  //divide by 1000000 to get milliseconds.

            if (keyGenerationDuration > 300)
                keyGenerationDuration = 300;

            data[j] = keyGenerationDuration;
        }
        DataService.writer(data, filename);
    }


    @Test
    public void testAESCipherDistribution() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla bibendum gravida feugiat. Fusce vitae nisl eget nulla cursus maximus a ut nisl. Nam ornare erat non turpis finibus luctus. Maecenas ac accumsan massa, ut pellentesque lorem. Etiam tempus lobortis efficitur. Aliquam laoreet, neque lacinia maximus iaculis, nisi dui tempus dui, et blandit ex ligula eget magna. Praesent ullamcorper mauris in nisl suscipit, vel imperdiet lacus lobortis. Vestibulum fermentum iaculis felis, vel mattis urna interdum eget. Nunc blandit semper felis in porttitor. In placerat tristique vehicula. Proin ultrices et nulla quis maximus. Vivamus vitae risus vitae risus tempus dapibus vitae eget est. Curabitur egestas sit amet lorem ut convallis. Sed auctor nibh at massa condimentum, et vestibulum metus ornare.\n" +
                "\n" +
                "Pellentesque sollicitudin congue finibus. In sollicitudin convallis magna, non vestibulum ante mollis quis. Praesent sollicitudin feugiat metus, ac auctor sem rhoncus nec. Sed id orci dolor. Suspendisse nec facilisis quam. In et porttitor erat, sit amet porta nisi. Curabitur varius nisi vel viverra dignissim. Duis quis libero porta, dapibus est eu, malesuada dui. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam est metus, maximus quis ex at, molestie euismod nunc. Proin suscipit pulvinar scelerisque. Aenean venenatis sit amet tortor id placerat. Suspendisse mattis hendrerit mauris in malesuada. Quisque auctor pretium nunc.\n" +
                "\n" +
                "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; In ut congue ex. Pellentesque id est sed dui varius sagittis volutpat non nibh. Suspendisse libero tortor, placerat eget aliquam quis, laoreet quis mauris. Aliquam rhoncus suscipit orci, ut euismod neque. Integer varius risus sed ultrices faucibus. Suspendisse eu lacus consectetur, ultricies purus at, accumsan nulla. Vivamus libero lorem, imperdiet ac fermentum vitae, ultrices pellentesque ipsum. Donec aliquam luctus nisi, non sagittis libero pulvinar eu. Aenean aliquam porttitor ligula, sed rutrum felis molestie at. Nunc consequat eget nulla ut blandit. Praesent bibendum mauris ac turpis vehicula sollicitudin. Nunc ipsum orci, pharetra sed quam nec, sodales imperdiet magna.\n" +
                "\n" +
                "Aliquam in ultrices ligula. Aenean ipsum purus, ornare quis cursus vel, rhoncus eu leo. Aenean mattis blandit consectetur. Nunc luctus odio at ante ullamcorper, volutpat sollicitudin metus scelerisque. Proin in semper enim. Donec non facilisis nisl. Nulla maximus, dolor ut sodales dictum, dolor nibh lacinia arcu, quis laoreet magna leo quis elit. Nullam vulputate massa condimentum mi pharetra tristique. Proin in ante molestie, sagittis libero at, sodales ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla facilisi.\n" +
                "\n" +
                "Mauris a mi urna. Sed convallis nibh turpis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Mauris eget augue urna. Donec ac purus nec ipsum faucibus mattis. Aenean neque lectus, iaculis in lacinia eget, commodo sit amet ex. Nam bibendum purus in odio sagittis venenatis. Ut vel quam nulla. Nullam rutrum dolor sed est bibendum hendrerit. Nam vel felis ex. In interdum felis ut nunc facilisis, auctor rhoncus risus tempus. Nunc quam magna, placerat at orci quis, mollis auctor nisl. Ut consectetur tincidunt pharetra. Maecenas accumsan, velit at convallis hendrerit, elit neque ultricies ex, vel ultricies justo tortor vitae mauris. Pellentesque sed mollis mauris. Suspendisse vitae ante quis urna eleifend molestie.\n" +
                "\n" +
                "Nulla lorem dolor, ornare quis lorem ut, imperdiet consectetur velit. Pellentesque tristique nisl sed varius porttitor. Nam tincidunt placerat mi, non interdum nisi egestas vel. Donec mattis pharetra faucibus. Integer tempor, odio eu cursus vehicula, ipsum nisl finibus purus, ac congue mauris justo non diam. Duis accumsan dolor felis, vitae bibendum odio tincidunt faucibus. Praesent ullamcorper nulla vel enim suscipit consequat. In sed arcu purus. Ut tristique felis nec ante egestas, sit amet pretium dui ultricies. Proin tristique magna ac orci imperdiet, id tincidunt tellus cursus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed eget sagittis diam.\n" +
                "\n" +
                "Sed sit amet sem in velit placerat consectetur elementum vitae ante. Integer eu mi mollis, congue nisi mattis, aliquam felis. Donec tempus elit non luctus consequat. Suspendisse quis semper orci. Vestibulum sed justo ut diam viverra congue id ac orci. Duis nec ligula diam. Ut eget diam nec ipsum vehicula hendrerit sit amet eget felis. Aliquam quam mi, pretium eu faucibus eget, feugiat vitae ipsum. Curabitur pharetra ligula non libero cursus, ac laoreet massa molestie. Integer ullamcorper ultrices neque rutrum pretium. Ut scelerisque dui id fermentum egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi hendrerit, ipsum a interdum vestibulum, arcu nunc scelerisque risus, ac maximus augue metus eu ligula. Quisque lacus felis, pharetra eget varius eget, faucibus eget nibh.\n" +
                "\n" +
                "Curabitur sit amet dui a libero ultricies maximus. Morbi orci tellus, iaculis ac ullamcorper eu, sagittis id velit. Aliquam erat volutpat. Donec imperdiet, tellus eu tincidunt vestibulum, leo sem euismod tellus, vitae bibendum risus lorem eget neque. Maecenas eu rhoncus quam. Donec faucibus semper porta. Quisque vestibulum felis metus, id efficitur mi ultricies ut. Nulla pellentesque eget erat nec faucibus. Proin vitae semper nunc, vitae mollis risus. Fusce bibendum turpis a dolor vehicula rutrum. Fusce a leo et leo placerat semper. Suspendisse potenti.\n" +
                "\n" +
                "Vivamus porta tincidunt dignissim. Pellentesque congue bibendum consectetur. Nullam pretium nisi venenatis magna finibus scelerisque. Maecenas at scelerisque ante. Fusce dictum odio a elit luctus, sit amet ornare sapien vulputate. Nulla ultrices velit sed quam rhoncus feugiat. Cras ultrices, est et sollicitudin porta, diam augue interdum nisl, lacinia scelerisque sem dui eu quam. Morbi et diam vel nibh scelerisque rhoncus sit amet laoreet orci. Pellentesque efficitur porttitor urna, non molestie elit pharetra eget. Nullam eu metus non erat ultricies gravida.\n" +
                "\n" +
                "Donec luctus mi felis, eu lacinia risus luctus quis. Aliquam blandit risus id magna eleifend, eget ultricies magna venenatis. Nunc cursus risus lorem, in ultrices quam bibendum non. Suspendisse aliquet lectus quis eleifend imperdiet. Curabitur nulla ipsum, convallis maximus velit quis, facilisis viverra risus. Nulla at ornare dolor. Integer ac dolor erat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec a sollicitudin dolor. Integer pretium tempor leo, vel ornare odio vehicula a. Nullam aliquam ipsum eu volutpat fringilla. Donec finibus, lectus posuere placerat accumsan, tortor enim maximus justo, a venenatis eros turpis in erat. Nam at commodo sapien. Integer ut eros non mauris scelerisque elementum eu viverra mi. Morbi tortor lorem, scelerisque eget diam eu, aliquet dapibus orci.\n" +
                "\n" +
                "Quisque semper ut enim id vehicula. Maecenas hendrerit neque id convallis blandit. In sed lectus ultrices lectus vehicula pulvinar. Phasellus pretium eget neque non fermentum. Sed elementum cursus posuere. Maecenas erat velit, placerat et auctor nec, ullamcorper a quam. Etiam magna risus, sagittis sit amet tortor sit amet, sollicitudin elementum urna. Sed eu odio ex. In a ligula at magna euismod rutrum. Aenean dolor enim, suscipit sed pretium et, porta eu massa. Donec ullamcorper sagittis neque at iaculis.\n" +
                "\n" +
                "Suspendisse ac orci tellus. Maecenas eros lacus, gravida ac rhoncus quis, feugiat volutpat orci. Nulla cursus nibh nec egestas sollicitudin. Donec risus lacus, ullamcorper at metus eget, commodo semper orci. Praesent imperdiet justo orci, vel mollis leo vestibulum at. Donec a elit at elit convallis vestibulum. Sed sed enim posuere, placerat elit at, tempor urna. Phasellus sodales leo convallis, tristique dui ut, semper urna. Vivamus cursus sodales justo. Aenean non eros viverra, tristique arcu ut, faucibus ligula. Sed mi arcu, accumsan eget leo nec, elementum condimentum lectus. Aenean enim urna, interdum sit amet ex vel, sodales posuere mi. Aenean pharetra pulvinar tempus. Phasellus felis velit, gravida in eros et, egestas pharetra turpis. Morbi sed purus in lacus tempor faucibus eget quis orci.\n" +
                "\n" +
                "Curabitur ante dui, sodales vitae aliquet a, interdum at nulla. Nulla sed urna augue. Sed ullamcorper lacus lorem, et semper quam dapibus ut. Mauris at lorem vitae nibh pharetra vestibulum non ut massa. Curabitur semper egestas dolor id dignissim. Proin aliquam, ante et lobortis mollis, magna sem viverra erat, ac rutrum arcu mi sit amet sapien. Curabitur vel dui massa. Nullam lobortis a mauris ut ullamcorper. Nulla consequat tincidunt ante, nec auctor mi rutrum eu. Fusce quis felis et turpis rutrum sagittis. Nulla facilisi. Proin id ipsum non tortor egestas mattis.\n" +
                "\n" +
                "Donec eget urna vitae elit varius sollicitudin. Donec condimentum tellus leo, id vestibulum enim placerat sed. Proin sodales felis id vehicula varius. Morbi erat tellus, fermentum rhoncus bibendum in, venenatis vitae lacus. Vestibulum dignissim, mauris vitae efficitur aliquet, dolor nisi gravida dui, eget eleifend lorem neque eget ex. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras turpis nibh, pretium sed sem ut, suscipit sollicitudin velit. Morbi leo turpis, accumsan ac purus nec, placerat cursus nisl. Mauris ut nibh aliquet, luctus justo at, condimentum tellus. Quisque et imperdiet turpis. Suspendisse fermentum nunc diam, quis eleifend lorem porta eu. Integer vel dui sed ex volutpat congue non sit amet est. In ut gravida dui.\n" +
                "\n" +
                "Phasellus at dui massa. Nulla suscipit iaculis est a egestas. Cras vel neque vulputate ipsum luctus feugiat sit amet vitae diam. Vivamus pharetra tellus ut libero posuere tempor. In placerat dui sit amet sem placerat commodo. Praesent feugiat dui sed metus dictum, a mattis leo euismod. Nunc quis elit elementum, suscipit odio quis, eleifend dui. Donec eu diam justo. Cras vel mattis leo. Mauris sit amet egestas risus.\n" +
                "\n" +
                "Curabitur non imperdiet turpis. Nullam ornare, magna at ullamcorper fermentum, ipsum nisi porta nisl, ut bibendum ipsum urna et turpis. Integer blandit risus in finibus pellentesque. Nunc sit amet finibus ex. Integer sed sagittis massa. Aenean porta malesuada urna nec dapibus. Cras ultricies, enim et mattis auctor, velit magna congue odio, eget mollis ligula nisl quis lorem. Donec hendrerit cursus enim ac dictum. Vestibulum ultricies mauris at magna posuere pellentesque. Pellentesque vestibulum nec augue ut tincidunt. Mauris sit amet bibendum erat, quis efficitur diam. Nulla vehicula dignissim tortor, quis lacinia orci dictum in. Donec pellentesque tincidunt malesuada. Vestibulum dignissim libero nec mi pellentesque, a vulputate purus varius. Maecenas sed pellentesque mi. Integer sit amet est vitae erat sodales finibus gravida feugiat dolor.\n" +
                "\n" +
                "Integer elementum nisi vehicula, porttitor elit eget, sollicitudin odio. Mauris non tellus ac purus semper bibendum. Aliquam ut ligula in ligula dignissim viverra. Fusce at gravida tellus, ac imperdiet lectus. Aliquam tincidunt gravida sodales. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque accumsan tristique magna vitae venenatis. Quisque posuere, elit eget convallis pretium, libero tortor pulvinar metus, vel feugiat erat magna ut risus. In hac habitasse platea dictumst.\n" +
                "\n" +
                "Quisque nec tincidunt felis. Nam sem lacus, accumsan sed eros sit amet, maximus sodales est. Nam vel lorem sit amet orci pellentesque sagittis. Quisque velit libero, pharetra eu felis non, blandit volutpat dolor. Nunc vel rhoncus massa. Ut vitae tristique metus. Donec et enim eget dui dignissim suscipit ut sagittis enim. Praesent hendrerit volutpat lorem, tempus pretium metus ultricies vestibulum. Donec urna mauris, porttitor ac posuere aliquet, fermentum et leo. Donec sollicitudin suscipit bibendum.\n" +
                "\n" +
                "Nullam lobortis porttitor lacinia. Etiam a sem scelerisque, volutpat augue nec, hendrerit urna. Mauris a sagittis leo, a fringilla sem. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin efficitur ut nunc quis tempus. Suspendisse euismod egestas nunc at mollis. Suspendisse posuere at tortor non consequat. Nunc ut rutrum purus, sit amet viverra nisi. Morbi sit amet pellentesque turpis.\n" +
                "\n" +
                "Nam lobortis vehicula lobortis. Fusce dignissim, quam iaculis cursus semper, leo nibh varius lacus, eget sollicitudin elit enim vitae ipsum. Aenean tincidunt blandit ante, sed placerat magna dapibus at. Donec enim arcu, convallis eget vestibulum vitae, tristique sed ex. Donec ligula turpis, ultricies sit amet quam et, efficitur dignissim augue. Suspendisse potenti. Vivamus at ultrices nisl.";

//        String input = "Test encryption speed";
//        String input = DataService.generateRandomString(12810);
        SecretKey key = AESService.generateKey(256);
        IvParameterSpec ivParameterSpec = AESService.generateIv();

        var cipherText = AESService.encrypt("AES/CBC/PKCS5Padding", input, key, ivParameterSpec);
        var filename = "files/histogram/aesCipherHistogramECB";
        var intInput = DataService.changeStringToASCII(cipherText);

        DataService.calculateHistogram(intInput, filename);
    }

    @Test
    public void testRSACipherDistribution() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException {
        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla bibendum gravida feugiat. Fusce vitae nisl eget nulla cursus maximus a ut nisl. Nam ornare erat non turpis finibus luctus. Maecenas ac accumsan massa, ut pellentesque lorem. Etiam tempus lobortis efficitur. Aliquam laoreet, neque lacinia maximus iaculis, nisi dui tempus dui, et blandit ex ligula eget magna. Praesent ullamcorper mauris in nisl suscipit, vel imperdiet lacus lobortis. Vestibulum fermentum iaculis felis, vel mattis urna interdum eget. Nunc blandit semper felis in porttitor. In placerat tristique vehicula. Proin ultrices et nulla quis maximus. Vivamus vitae risus vitae risus tempus dapibus vitae eget est. Curabitur egestas sit amet lorem ut convallis. Sed auctor nibh at massa condimentum, et vestibulum metus ornare.\n" +
                "\n" +
                "Pellentesque sollicitudin congue finibus. In sollicitudin convallis magna, non vestibulum ante mollis quis. Praesent sollicitudin feugiat metus, ac auctor sem rhoncus nec. Sed id orci dolor. Suspendisse nec facilisis quam. In et porttitor erat, sit amet porta nisi. Curabitur varius nisi vel viverra dignissim. Duis quis libero porta, dapibus est eu, malesuada dui. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Aliquam est metus, maximus quis ex at, molestie euismod nunc. Proin suscipit pulvinar scelerisque. Aenean venenatis sit amet tortor id placerat. Suspendisse mattis hendrerit mauris in malesuada. Quisque auctor pretium nunc.\n" +
                "\n" +
                "Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; In ut congue ex. Pellentesque id est sed dui varius sagittis volutpat non nibh. Suspendisse libero tortor, placerat eget aliquam quis, laoreet quis mauris. Aliquam rhoncus suscipit orci, ut euismod neque. Integer varius risus sed ultrices faucibus. Suspendisse eu lacus consectetur, ultricies purus at, accumsan nulla. Vivamus libero lorem, imperdiet ac fermentum vitae, ultrices pellentesque ipsum. Donec aliquam luctus nisi, non sagittis libero pulvinar eu. Aenean aliquam porttitor ligula, sed rutrum felis molestie at. Nunc consequat eget nulla ut blandit. Praesent bibendum mauris ac turpis vehicula sollicitudin. Nunc ipsum orci, pharetra sed quam nec, sodales imperdiet magna.\n" +
                "\n" +
                "Aliquam in ultrices ligula. Aenean ipsum purus, ornare quis cursus vel, rhoncus eu leo. Aenean mattis blandit consectetur. Nunc luctus odio at ante ullamcorper, volutpat sollicitudin metus scelerisque. Proin in semper enim. Donec non facilisis nisl. Nulla maximus, dolor ut sodales dictum, dolor nibh lacinia arcu, quis laoreet magna leo quis elit. Nullam vulputate massa condimentum mi pharetra tristique. Proin in ante molestie, sagittis libero at, sodales ligula. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla facilisi.\n" +
                "\n" +
                "Mauris a mi urna. Sed convallis nibh turpis. Interdum et malesuada fames ac ante ipsum primis in faucibus. Mauris eget augue urna. Donec ac purus nec ipsum faucibus mattis. Aenean neque lectus, iaculis in lacinia eget, commodo sit amet ex. Nam bibendum purus in odio sagittis venenatis. Ut vel quam nulla. Nullam rutrum dolor sed est bibendum hendrerit. Nam vel felis ex. In interdum felis ut nunc facilisis, auctor rhoncus risus tempus. Nunc quam magna, placerat at orci quis, mollis auctor nisl. Ut consectetur tincidunt pharetra. Maecenas accumsan, velit at convallis hendrerit, elit neque ultricies ex, vel ultricies justo tortor vitae mauris. Pellentesque sed mollis mauris. Suspendisse vitae ante quis urna eleifend molestie.\n" +
                "\n" +
                "Nulla lorem dolor, ornare quis lorem ut, imperdiet consectetur velit. Pellentesque tristique nisl sed varius porttitor. Nam tincidunt placerat mi, non interdum nisi egestas vel. Donec mattis pharetra faucibus. Integer tempor, odio eu cursus vehicula, ipsum nisl finibus purus, ac congue mauris justo non diam. Duis accumsan dolor felis, vitae bibendum odio tincidunt faucibus. Praesent ullamcorper nulla vel enim suscipit consequat. In sed arcu purus. Ut tristique felis nec ante egestas, sit amet pretium dui ultricies. Proin tristique magna ac orci imperdiet, id tincidunt tellus cursus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Sed eget sagittis diam.\n" +
                "\n" +
                "Sed sit amet sem in velit placerat consectetur elementum vitae ante. Integer eu mi mollis, congue nisi mattis, aliquam felis. Donec tempus elit non luctus consequat. Suspendisse quis semper orci. Vestibulum sed justo ut diam viverra congue id ac orci. Duis nec ligula diam. Ut eget diam nec ipsum vehicula hendrerit sit amet eget felis. Aliquam quam mi, pretium eu faucibus eget, feugiat vitae ipsum. Curabitur pharetra ligula non libero cursus, ac laoreet massa molestie. Integer ullamcorper ultrices neque rutrum pretium. Ut scelerisque dui id fermentum egestas. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi hendrerit, ipsum a interdum vestibulum, arcu nunc scelerisque risus, ac maximus augue metus eu ligula. Quisque lacus felis, pharetra eget varius eget, faucibus eget nibh.\n" +
                "\n" +
                "Curabitur sit amet dui a libero ultricies maximus. Morbi orci tellus, iaculis ac ullamcorper eu, sagittis id velit. Aliquam erat volutpat. Donec imperdiet, tellus eu tincidunt vestibulum, leo sem euismod tellus, vitae bibendum risus lorem eget neque. Maecenas eu rhoncus quam. Donec faucibus semper porta. Quisque vestibulum felis metus, id efficitur mi ultricies ut. Nulla pellentesque eget erat nec faucibus. Proin vitae semper nunc, vitae mollis risus. Fusce bibendum turpis a dolor vehicula rutrum. Fusce a leo et leo placerat semper. Suspendisse potenti.\n" +
                "\n" +
                "Vivamus porta tincidunt dignissim. Pellentesque congue bibendum consectetur. Nullam pretium nisi venenatis magna finibus scelerisque. Maecenas at scelerisque ante. Fusce dictum odio a elit luctus, sit amet ornare sapien vulputate. Nulla ultrices velit sed quam rhoncus feugiat. Cras ultrices, est et sollicitudin porta, diam augue interdum nisl, lacinia scelerisque sem dui eu quam. Morbi et diam vel nibh scelerisque rhoncus sit amet laoreet orci. Pellentesque efficitur porttitor urna, non molestie elit pharetra eget. Nullam eu metus non erat ultricies gravida.\n" +
                "\n" +
                "Donec luctus mi felis, eu lacinia risus luctus quis. Aliquam blandit risus id magna eleifend, eget ultricies magna venenatis. Nunc cursus risus lorem, in ultrices quam bibendum non. Suspendisse aliquet lectus quis eleifend imperdiet. Curabitur nulla ipsum, convallis maximus velit quis, facilisis viverra risus. Nulla at ornare dolor. Integer ac dolor erat. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Donec a sollicitudin dolor. Integer pretium tempor leo, vel ornare odio vehicula a. Nullam aliquam ipsum eu volutpat fringilla. Donec finibus, lectus posuere placerat accumsan, tortor enim maximus justo, a venenatis eros turpis in erat. Nam at commodo sapien. Integer ut eros non mauris scelerisque elementum eu viverra mi. Morbi tortor lorem, scelerisque eget diam eu, aliquet dapibus orci.\n" +
                "\n" +
                "Quisque semper ut enim id vehicula. Maecenas hendrerit neque id convallis blandit. In sed lectus ultrices lectus vehicula pulvinar. Phasellus pretium eget neque non fermentum. Sed elementum cursus posuere. Maecenas erat velit, placerat et auctor nec, ullamcorper a quam. Etiam magna risus, sagittis sit amet tortor sit amet, sollicitudin elementum urna. Sed eu odio ex. In a ligula at magna euismod rutrum. Aenean dolor enim, suscipit sed pretium et, porta eu massa. Donec ullamcorper sagittis neque at iaculis.\n" +
                "\n" +
                "Suspendisse ac orci tellus. Maecenas eros lacus, gravida ac rhoncus quis, feugiat volutpat orci. Nulla cursus nibh nec egestas sollicitudin. Donec risus lacus, ullamcorper at metus eget, commodo semper orci. Praesent imperdiet justo orci, vel mollis leo vestibulum at. Donec a elit at elit convallis vestibulum. Sed sed enim posuere, placerat elit at, tempor urna. Phasellus sodales leo convallis, tristique dui ut, semper urna. Vivamus cursus sodales justo. Aenean non eros viverra, tristique arcu ut, faucibus ligula. Sed mi arcu, accumsan eget leo nec, elementum condimentum lectus. Aenean enim urna, interdum sit amet ex vel, sodales posuere mi. Aenean pharetra pulvinar tempus. Phasellus felis velit, gravida in eros et, egestas pharetra turpis. Morbi sed purus in lacus tempor faucibus eget quis orci.\n" +
                "\n" +
                "Curabitur ante dui, sodales vitae aliquet a, interdum at nulla. Nulla sed urna augue. Sed ullamcorper lacus lorem, et semper quam dapibus ut. Mauris at lorem vitae nibh pharetra vestibulum non ut massa. Curabitur semper egestas dolor id dignissim. Proin aliquam, ante et lobortis mollis, magna sem viverra erat, ac rutrum arcu mi sit amet sapien. Curabitur vel dui massa. Nullam lobortis a mauris ut ullamcorper. Nulla consequat tincidunt ante, nec auctor mi rutrum eu. Fusce quis felis et turpis rutrum sagittis. Nulla facilisi. Proin id ipsum non tortor egestas mattis.\n" +
                "\n" +
                "Donec eget urna vitae elit varius sollicitudin. Donec condimentum tellus leo, id vestibulum enim placerat sed. Proin sodales felis id vehicula varius. Morbi erat tellus, fermentum rhoncus bibendum in, venenatis vitae lacus. Vestibulum dignissim, mauris vitae efficitur aliquet, dolor nisi gravida dui, eget eleifend lorem neque eget ex. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras turpis nibh, pretium sed sem ut, suscipit sollicitudin velit. Morbi leo turpis, accumsan ac purus nec, placerat cursus nisl. Mauris ut nibh aliquet, luctus justo at, condimentum tellus. Quisque et imperdiet turpis. Suspendisse fermentum nunc diam, quis eleifend lorem porta eu. Integer vel dui sed ex volutpat congue non sit amet est. In ut gravida dui.\n" +
                "\n" +
                "Phasellus at dui massa. Nulla suscipit iaculis est a egestas. Cras vel neque vulputate ipsum luctus feugiat sit amet vitae diam. Vivamus pharetra tellus ut libero posuere tempor. In placerat dui sit amet sem placerat commodo. Praesent feugiat dui sed metus dictum, a mattis leo euismod. Nunc quis elit elementum, suscipit odio quis, eleifend dui. Donec eu diam justo. Cras vel mattis leo. Mauris sit amet egestas risus.\n" +
                "\n" +
                "Curabitur non imperdiet turpis. Nullam ornare, magna at ullamcorper fermentum, ipsum nisi porta nisl, ut bibendum ipsum urna et turpis. Integer blandit risus in finibus pellentesque. Nunc sit amet finibus ex. Integer sed sagittis massa. Aenean porta malesuada urna nec dapibus. Cras ultricies, enim et mattis auctor, velit magna congue odio, eget mollis ligula nisl quis lorem. Donec hendrerit cursus enim ac dictum. Vestibulum ultricies mauris at magna posuere pellentesque. Pellentesque vestibulum nec augue ut tincidunt. Mauris sit amet bibendum erat, quis efficitur diam. Nulla vehicula dignissim tortor, quis lacinia orci dictum in. Donec pellentesque tincidunt malesuada. Vestibulum dignissim libero nec mi pellentesque, a vulputate purus varius. Maecenas sed pellentesque mi. Integer sit amet est vitae erat sodales finibus gravida feugiat dolor.\n" +
                "\n" +
                "Integer elementum nisi vehicula, porttitor elit eget, sollicitudin odio. Mauris non tellus ac purus semper bibendum. Aliquam ut ligula in ligula dignissim viverra. Fusce at gravida tellus, ac imperdiet lectus. Aliquam tincidunt gravida sodales. Interdum et malesuada fames ac ante ipsum primis in faucibus. Pellentesque accumsan tristique magna vitae venenatis. Quisque posuere, elit eget convallis pretium, libero tortor pulvinar metus, vel feugiat erat magna ut risus. In hac habitasse platea dictumst.\n" +
                "\n" +
                "Quisque nec tincidunt felis. Nam sem lacus, accumsan sed eros sit amet, maximus sodales est. Nam vel lorem sit amet orci pellentesque sagittis. Quisque velit libero, pharetra eu felis non, blandit volutpat dolor. Nunc vel rhoncus massa. Ut vitae tristique metus. Donec et enim eget dui dignissim suscipit ut sagittis enim. Praesent hendrerit volutpat lorem, tempus pretium metus ultricies vestibulum. Donec urna mauris, porttitor ac posuere aliquet, fermentum et leo. Donec sollicitudin suscipit bibendum.\n" +
                "\n" +
                "Nullam lobortis porttitor lacinia. Etiam a sem scelerisque, volutpat augue nec, hendrerit urna. Mauris a sagittis leo, a fringilla sem. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Proin efficitur ut nunc quis tempus. Suspendisse euismod egestas nunc at mollis. Suspendisse posuere at tortor non consequat. Nunc ut rutrum purus, sit amet viverra nisi. Morbi sit amet pellentesque turpis.\n" +
                "\n" +
                "Nam lobortis vehicula lobortis. Fusce dignissim, quam iaculis cursus semper, leo nibh varius lacus, eget sollicitudin elit enim vitae ipsum. Aenean tincidunt blandit ante, sed placerat magna dapibus at. Donec enim arcu, convallis eget vestibulum vitae, tristique sed ex. Donec ligula turpis, ultricies sit amet quam et, efficitur dignissim augue. Suspendisse potenti. Vivamus at ultrices nisl.";

        var maxBytes = 245;
        KeyPair keyPair = RSAService.generateRSAKeyPair();
        StringBuilder encryptedInput = new StringBuilder();
        for (int i = 0; i < input.length(); i += maxBytes) {
            encryptedInput.append(RSAService.encrypt(input.substring(i, i + maxBytes), keyPair.getPublic()));
            if (i + maxBytes * 2 > input.length())
                break;
        }

        var filename = "files/histogram/rsaCipherHistogram";
        var intInput = DataService.changeStringToASCII(encryptedInput.toString());

        DataService.calculateHistogram(intInput, filename);
    }
}