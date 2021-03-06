package www.bwsensing.com;

import org.junit.Assert;
import org.junit.Test;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.mockito.internal.util.MockUtil;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import www.bwsensing.com.common.utills.HexUtils;
import www.bwsensing.com.common.utills.RSAUtils;

/**
 * @author macos-zyj
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.crypto.*")
@PrepareForTest({MockUtil.class,SensorApiDataRsaTest.class,RSAUtils.class})
@Slf4j
public class SensorApiDataRsaTest {
    private static final String ENCRYPT_HEX_STRING = "a4dd198fa3a0d9b2230b57f6ccd4c538f7fd6c9f9e5c751619d4d76a8db2b8c7fe3d962ed8fde3d4ec02e8446f6ca58619f2cab34441ae2bebed3a2c8bda363121326" +
            "8a6bbca9558f629e6bf3909de565de9074aac3d3203de83753ca6384b2ef30e4b0ec07503451796c4f835694b89205969c82c5e50f59163ce9db5e1ef2f7829e4a88acebf55fe6992263093b24a14c929d262f5e74d3016" +
            "9bd6da099d72d66b1b95920695df48bcd40618f557724d157e2a02c7433204fbb9fd3387059f0c0aa19d906242e244a7e71b2c443d419034004c5b5627cd144b5fcc00b81c21a584179da30651e16bac4b49f357df18dcdb" +
            "7bbfb110fc2a369e0b5cc402e4f96651cf63ae20739e9ad11a7e624f574acdccc37497d398c155ad3d97f6a72387c5def6aafd2d0ac06d698690e33ae6e6b2edff3c6c3ee90185deb708b9e1a7a38e133f8c21cf8ae3228ef" +
            "7480874b97d05497cdbc5b507ba0215f5cf0eef96f8b818f314c7173dae29e48b68776266bfc2caaeb419ed591f90574832c178b57e0e45df29b6bb118926bfdc9e1a609dc05ee717a53d390ef69059a5435af67f5cd0e46e9" +
            "11fc687987362d131dc014b55c70e69fcccc4eab2160b0c985b3e484fe89ae3eb0517d35f5b521d7949566a73fad49ceee9dedbbc3489cf50814822396cd21a5d4097b0439fb5f35070ce85665142e1ad95344007a06c66d7cd56df" +
            "9e711933d154ded6d44feec3ad3ce03ca07a65247285bbf9a65509a09201f35272d0b36da16fd1164b84787388ac0a809dc1cc6705b7cce12a69883c3ac781dffc33efcd2dd98e14a0f106df3dc96aaf98bbccbbe9b9e4017d2fca7c21" +
            "73e9e5d3120fa37b923c201b6d9d44add3d5c73fca7317459e68e6202d2c0973fcf4b29093ab0463b89694e4219a46e6161ad99d64b6879a39b6a8838396c5d50eabd195218a72cf1b0bc1c6ed96e0a5de33226c766e6dac58977cb8a384" +
            "2f85423a1e4daa3259025cd4f33b70af22bd708a749d6f46fc91f2740fefd712805842fe0463c413f0fb8382a9a3b9679ee80bd4a4b5b0b9eb02c84c911a9e156aa2bb0b99de7de52b2ff3e2cd9909c2ad72908a356aacec7f0965aad9d7f28" +
            "36e9d13b1eb556e193e2e4044935f12cb9ef4e176e04a6edce15a68cebd9dc0b00e78731b391152811fb1d73cad0709a0fc41c2e07ef1090d407433b250734edb3ac17ec9d1794d51d7d765b33c7f9257138ff0b917617bc7de5a0fb36aa0e5151c" +
            "ab1d909c88509f5f9cee220f4d8d305f1d40d4396b1fb6c3df6d60222697acdba11f33c6cae5450e6e307b8dc45b2e42aa88cb645bdf055c9842d699361c68423dcba23707eda9e11c332d44a5879a2a3104fd1be8d6e8fca90b1f2cbf1e63ab97874" +
            "ea42b949164eb47d8d11347606196c2da7a43b910fbc5f83e03f1eb0160dc6c8c3c97b25d82f78996fb94d735f68552c9671d3a55219e96ca8c6751cb8247c5c3b96d54a61366d88fa2ca02bc72f57429323878af5880e233bdbdc2892b89e205499365b" +
            "44f6fef5e5dc5934770f9eabb781c3a4f255efe06a74a25527d3efe9cfa1aafdc3a0167f9cafd8704351f0c005f808e34984189c75758139692a257ce35544243f537d376991ee82c9a48480dbc8404c34034c7206073a5294d1dea1e6a4ab3219587d284e29" +
            "a11ec12f17a79b1100dfa962fcce378613159da44b25c9a4193eb41ddd9d4438071c5af946fc84e67102ff8ab6bcb131a22a277e6711f40b43769d8f06880860a47884894cd951e2d121648caf05173abb274b32c2dae9233ac0a1af0521e95896dacd6d2a913c7" +
            "10d1b9940c33fdbdd78192c7544b3a6776d39cab5c6faa76ca3a93c95e53c0cdc69fd889b55155587008a3479ff90937977cf74faf9a2d8a1a81a1cfc9e8b70fc00540be1cc3ef1b246246f161d9db6321012bfac8c21abad4bf08e359e20f4833c4156bec18eba5f3d1f" +
            "73fc8cb4b001caa28bc875b30e4cfa0e4b62e68ab91dc4e740c24ae40da93ff54dc5ef9ad4e70da0325744f603ecb987510f67b5d0f6d318d8af2f700ee618db163506e8a1c277ff547a1bf6f0cb2eb7753885da179b5ca870d48cb2be4126e644dfc18ae37" +
            "f972b482db4ac368d0ff9a993555b0d9759815aac1bb43895899152b5ceb8979557251a12118a3c12f405d829962a65393264ceb6d81531c5011df344e05bba9638a14fe551f2ad14210eb6b88bc5ef6ce5a035d5f95ed0f6826bd922a1603c5ef5d1a743a199" +
            "210f4379a0b6fc54222530d44f64a8d9a314689f9a3673b01a7d272cbba025d7dadb438b9b6c0d08b34f4cdf5ff620fb8edf7cb364e05d20b9198af463d0bf4508bbac738c3ba44ffe4a94b322d224ded7bd35a9d046782dd96b701753860063a21fe5950ab9a0f08" +
            "003154b6dea6c33bfc27a4f180c14d32288926da1f6a180bb74ef2133a6e495ad00ef5d9a167597811010aa504d5589fa1a8178e0fcd8bd9f4dfbaa15e5e46a310fefb2794d75522415155e9cf023fed7bf260a06b65fb3cd0277c74bf65f4f9c026269b62457cf1eeb7a3" +
            "a3494a8d3a3b175ccf941906ada99bb830b19f6249ca1fdf0a36b0decd00c9e1044c581d361dded71cba2c725feebfb6ae5e9fee107fd0962a242d12e0b06a286ddad7d6e94b0d959b56654f94a2f7e1c71d44c531041bae23fe18994ce95f0c8eb0a1c1e2887f3e333315d0f3" +
            "293c3440b68b60a9223d87d6780710b5da04bfaeb61ab25611383c1244dffedaac42c55bca3694e26581bfcd58d8dd8ee937dd3cc28c0994e6538742d70a6e29cfe0ce957143e40252f9b6b799578a0c958b8a2554ae9799047dee8fe7a5e13dcac7d94c8efe239d9bc96431a0e55" +
            "3f333f2df778ede303332ce48097fc383d2a984b4348ee3fc91e0cf476f597a71e1a845b294dbc104011a762f5d224661b809a20f4cc4a6530cfbe823bf7dab4ca173dfc1cdd35787815b5a6c619ee1537f31ffa87759648c9752add5a0cfa87f8da9aec710ecc7dfc7fe5bef1ff2c0ac12799d67258d35e3220328b48df0cb2c215abfe0df291bb121c9349963fbce16e59c019765aa6952ec40fb2e267f926cb5721c21437dba71c3b4e2bd83011b9d8dba9848c940ee4ed31744a29cb71e2aa30272f838d96dfe2da67cddf64ef3f49e85701a9600dc78d576a77ed944fc6a99a91a8106240504a4dad1853bc0ba6a136c6a6124a9ddf333946b18ca30894a770597fb96c6a86d39921907d43e5e2adda74540eb3d77582f39e51a5cc30b402974827fd301e1532c5f10d210fdf878ffdfc25a5a0a092eee2752390511f73c0084441c620431aa84ac21576ff1058f7ef2fd102f50566db1080728e275096b9a3871368d161268c5a6f9c9cf537062250ccb11d59e1950c83a1c425d6b875ef26cef901d93d8f65301e15bfa44e1d235635d0677dcd4617cf5b8584e6dfe038fd2deab3c3fd276967db2be15b72004ff24e135ad7097d10c4e7164cbc18c1978d4736264b46833d35d3c3e43cf9712332106c2a6ed2bfe166ec58cf696de603ee0f441cbd75cb28969ccedc02d5d2eaaf0e36a14b85bf430334213d76a2c86d9dc0d271d627bee96d3944080ec65806a3bba6e18754e1efbbace5fdbea701c893c8b71aaccd83deed216c0cfe0df5a3ccf59e5a822f10b30fdac94c62b54b373de9e8c292c55902dc52efdc986caaf853704c2786bca74957c943e79076a2ef536ec9842b2307584c8b6aeccc0b9e8a84b4a34ab405043d0aedf2af0bc0a199c6e8fc22bb353b710bdad66881a1f21616f21d221072dd50c04cd0acef3067415ac4c22883f49c09d012d0963c14f30845abd8b45a56773a0dff04074f7856e1007836b21f3f165ba507e996fa0a6ab3214987a73f953dc2c13a66117198019a52fd1f8a624d979561a4e8f9b65ce173d2b1dc2df4bcbbf7f007d36c8ca7ab4852b1666767746a155c20597cd1806817ecbf5d2fd65d75cdc17a185a17cf3aee0e2c8e0afbb59355c1306aa2d2fae8086fb895dd3cc0f825ca8c6252657ab6d7f81392072967e0d333954c5c3823c2cd6352e7d327ddf9f2d786fc46f9b5d962f11cf28b90aa601b770b9264270230b0e35e5233beabb276d7630a41f4d27fdc764a673e68c296c99208b64552224d451dad08abc94fceef8671f802545443671cc9d68f90cc841398184dfef9bdac71616bf3c6c8365c3dfce558745ef5e4b1e4920d5847f7666f98b2ad2ed7334cd40df745ca2f2b00fa68ae715fda9148fc6188d8b8b594e4515e557f596b2e4685cb2fe12175d541ed40f5ddd71189e0f34caf73c2d8c4bcb2c83aa467914317d1fe95589ac28bcf74a6d1b95a041dcdb53579f419bb690bb33c370477a51b65d245f3500e066b175b930d969cd0e342bf89687d5321a548762884f3fbf16b2c851d914a6cb8b4626272e3e0fa4c13ba1b4b3e53d74af8cbe1346d69dfa342cdf1b06b958af6de9ed70c7b19bd4b3d4d2d54db3061396562ae131a33dbf125b37cbd904c3748c2195a5b0ab2f3b99f28c81d9e70c894f685a73bbc0b5a9598937cf1420fdf8d0da2f8286dc7843c638a6a2114626f393114bfde84651296a987f4a1569a90298569488d0d15bc9b728b2e4995233380a6959a59cdaaacbc083f2a6fdc89de6a88def6f5ba3ae84b5000fbaccde2b02e114af8b5a5e57570b965b87d254cc90afb02e052b822bd8a9cd680a0a2d47ba0c870fb816150a5c20305d58436278bfc11c82a5ad780c1bfee6a5bd8247a2c0333188a6ea34cdb405424b2ec365be79e6898f9b672e170d7603d7162e17f2069ee9136e9429078f506b4343a8e9a2fdae90ca08b66bc3c5298405fa52469064f38731b779df90b9faff2a90f1c4b6293cd8209ebb4395db9720d48acd113c11e14bd5adffe4db0752571703da4d83ef2d5f84be6f622bad80b99827189e2cd391e29c039fcddac2a6eedb2e2a8eedbec54b254490529a353f7587d90f9e279f9d76a809e8f33b4aaf1abb4c62342086cc5a17fcef28d5922c1898bf5e85c9b0a7690c26f7e041d81f7630876eab31f22d6c596edb416d87cbe5bfffa64c3ad2c86bd7eb7efacc043d5a226ac149cbe8e36655b06896503c9fe245886b1c393e76c1b2ddab13e1745b0ec290ec5d2d1a31848f67ff2d7f52059fe018a2e53db413a3515ff411ac1023157ca7b43d05a27d3b528269f8ce023265df94f60fef47932f773cf6db7291ff0a45cef279209daae6ab43611c57ce89d2f3e204f5db8e9199d30864604dd949c54bd0f6f551e5cf42a4abc5d437aa24a179faa50ea902e2f0eedad2e2e64eb3f950a1a5420ebbb9024fdc818783ef313e64d77a0ca67407e7a383fa4995880a3d3b4421796115afa6ac118ebd4113745c78c7781de5b0f2a4a0116a3516b9781315463a57d748456c2b65222af3d103f1459757b1200dd0dbccd482a89d119d64d7142af7708ce3dc5adfe2afaf80aa25647710ca9c4387972b760281f3481de129173be94d765b1d6a6bacce5dbb8cbc505a31408a38c020d962776091b33a89353acd5b1ea46b8b83d70791fa7111ab6f3d446e9cf094d4998d4a1a5635b8edfe8c122f5b92255db2effb68a79c4427565fdda5629168de72007782b766b802050e607080133ddddb43dc87c5e7437673a5467e1b02290cce82642e4aecf302f964ef85640ffb95907a4c8d993039a3dfa5605e634015a36414f71329707a104b449b3823d9dfaa7bc7a297399fbb6dd68bb93f9bcfda21260d9d395b9a57c0fc828c2134fa13a9c0a4d96f246474b269620bfad34cb5aa01a4af0e82c1d7a0683a0328efa9cefcc3cac34fb4bf5f13efa1a089411b2ecbc384a9b7baba8b14bb8f632d4e067016ddd83fe0563d7d8ab934b45e5b63df4a9440e69ff9e14226679f542670d6cbb1738ae1faf9f55c4e0e45ffd4621184d812d15b2525c1361eb87b7a6d4f19cb62be5e21a6243264b0e7943fa517e97bc011a920429ee227db80d10f880b91946d0743a9b4a913b10331404fe8a3b63c3c891882deed0db18ea1d77a44cb799a4781fd2c0891966d13cc6d04d79cc7a813108a196819ef54916cb9f09af7230e2786f8d075de377d2d1da03d51cfa3f6017094b9344d3fa825ba6f86c5f09f2d192a1876c7f2b3ed734b09e23b0fa55d6ecffbab0aa094d2301bdc8548ef35654737d8713a316b16059ff684fc0b380d19cc10c30cf6e3e64bb7ed36266f3d3b2eae5fcef801276e64f1d1c764a08bd51a844120c571680df8d1a0ec9997cad6f3a634f1190667de6e562fb7119a9f9b111c7821f3723e542ddb47c5970be12f9d94aae55920008d58156bac2ac351804cb87f1ed2c080003579d4cb425279ad903f3714e717639f80f565dc73f0e8b56b830e6f6eb0e88ad6d87d57ecbf98d8a88de6e8ef2d5a4ef9418f2f30938f8412c43edcb94ddd01ba4d602ca89d8f97ef6435692acd3827e02027735ae593ffa9833220e2b181bd9c5c647b4e89087c6253324986d7f7e016afecc238c55bce1c2a8c8b03e0a6ca20b5f0a7cb52785725d1bcc4a6719569e4761183137cd53e3d80f62e3b754f7202d8a1c04152af312e2b26a76e8d661b1b39f9088185164d05a19168a10865311e4cd24d528944cfb308e3fb891dccd90ea859e3bf850e9b6d021b5dec";
    private static final String TEST_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALdKyy3A9BSRMgZcX9jLII6MKbFomXQarC2jsRrryc+gUx/wE5MlEUsw1M25EoRtk7phzIEE8WwcxA5tMZZcgsEKVZxXj/qo1xw3cq05Niww1XXZHP6L6QZKkfhrypmde7hIATNmIxoaPD7cpHxHHQ5NmJRvSNLjSu466yxOcOePAgMBAAECgYAVhiL17IK9J4/IT3Cx9KiOFMeQncfnanptoZtanfXE+/7G8VpbHS86tTFIN0OFaZi/elyZigsPtxEO4KPCFHsGHVAyIWwcL1WBn+suLkgF6U6XGQi2Emd5cHO4Q1JbwDXmsEEhuvS0eHgbMo76VNRuzq2gz1WDexR4tiCvKe8CAQJBAOO5j48LaaLPN66dMB4DZ2y03mt/BYDEaSXsWkIAQ9dOIaXa6vOxCxL7BtsHQgxbrk64oteORyYnB9gtUWULUfECQQDODOZoxqRVzumtIaj3VTlHb+EnXqr4EIcO65ThljU5FWioRq7DqW3buelqH7nWNL8pCgGpFO4uPVITIKxBRlF/AkEApqjYKq0Ei3Wx36YEYALHv2QDX5KfmWSlO27lg2YkQD9bGsHDr8NDQonas5N1QAwc3ln8O1tSMNbhw9Euv49wUQJABrs7J78djUQWNDa+S144YdIQ2/VmxnXbw6wEkB4VHQlLAvxUJ0Y4tY3sRzBEJPBSMmgGR9zMLyyHquJNXlCKxwJBALPFo9V1Fmquj13AUF20/9rjVDhSReP6HpKnBI3Vuh9lkYdUPshSK9u7+u5ZhCmUN3Mt5LB0mpCN2kVV5osxHoU=";
    private static final String EXPECT_DATA_RESULT = "[{\"modelName\":\"???????????????\",\"uniqueCode\":\"100000005\",\"dataItems\":[{\"itemName\":\"X?????????\",\"unit\":\"??\"," +
            "\"dataId\":\"x_ang\",\"id\":1},{\"itemName\":\"Y?????????\",\"unit\":\"??\",\"dataId\":\"y_ang\",\"id\":2},{\"itemName\":\"Z?????????\",\"unit\":\"??\",\"dataId\":" +
            "\"z_ang\",\"id\":3},{\"itemName\":\"X????????????\",\"unit\":\"g\",\"dataId\":\"x_acc\",\"id\":4},{\"itemName\":\"Y????????????\",\"unit\":\"g\",\"dataId\":\"y_acc\"," +
            "\"id\":5},{\"itemName\":\"Z????????????\",\"unit\":\"g\",\"dataId\":\"z_acc\",\"id\":6},{\"itemName\":\"??????\",\"dataId\":\"elect\",\"id\":7},{\"itemName\":" +
            "\"??????\",\"unit\":\"??C \",\"dataId\":\"temp\",\"id\":8}],\"dataMap\":{\"y_ang\":{\"dataValue\":0.557,\"ts\":1648289550434},\"temp\":{\"dataValue\":86.4531," +
            "\"ts\":1648289550434},\"x_ang\":{\"dataValue\":3.4982,\"ts\":1648289550434},\"z_ang\":{},\"x_acc\":{},\"y_acc\":{},\"elect\":{\"dataValue\":0.0601," +
            "\"ts\":1648289550434},\"z_acc\":{}},\"name\":\"???????????????1\"},{\"modelName\":\"???????????????\",\"uniqueCode\":\"100000003\",\"dataItems\":[{\"itemName\":" +
            "\"X?????????\",\"unit\":\"??\",\"dataId\":\"x_ang\",\"id\":1},{\"itemName\":\"Y?????????\",\"unit\":\"??\",\"dataId\":\"y_ang\",\"id\":2},{\"itemName\":\"Z?????????\"," +
            "\"unit\":\"??\",\"dataId\":\"z_ang\",\"id\":3},{\"itemName\":\"X????????????\",\"unit\":\"g\",\"dataId\":\"x_acc\",\"id\":4},{\"itemName\":\"Y????????????\",\"unit\":\"g\"," +
            "\"dataId\":\"y_acc\",\"id\":5},{\"itemName\":\"Z????????????\",\"unit\":\"g\",\"dataId\":\"z_acc\",\"id\":6},{\"itemName\":\"??????\",\"dataId\":\"elect\",\"id\":7}," +
            "{\"itemName\":\"??????\",\"unit\":\"??C \",\"dataId\":\"temp\",\"id\":8}],\"dataMap\":{\"y_ang\":{},\"temp\":{},\"x_ang\":{},\"z_ang\":{},\"x_acc\":{},\"y_acc\":{}," +
            "\"elect\":{},\"z_acc\":{}},\"name\":\"???????????????2\"},{\"modelName\":\"???????????????\",\"uniqueCode\":\"100000067\",\"dataItems\":[{\"itemName\":\"X?????????\",\"unit\":\"??\"," +
            "\"dataId\":\"x_ang\",\"id\":1},{\"itemName\":\"Y?????????\",\"unit\":\"??\",\"dataId\":\"y_ang\",\"id\":2},{\"itemName\":\"Z?????????\",\"unit\":\"??\",\"dataId\":\"z_ang\",\"id\":3}," +
            "{\"itemName\":\"X????????????\",\"unit\":\"g\",\"dataId\":\"x_acc\",\"id\":4},{\"itemName\":\"Y????????????\",\"unit\":\"g\",\"dataId\":\"y_acc\",\"id\":5},{\"itemName\":\"Z????????????\"," +
            "\"unit\":\"g\",\"dataId\":\"z_acc\",\"id\":6},{\"itemName\":\"??????\",\"dataId\":\"elect\",\"id\":7},{\"itemName\":\"??????\",\"unit\":\"??C \",\"dataId\":\"temp\",\"id\":8}]," +
            "\"dataMap\":{\"y_ang\":{},\"temp\":{},\"x_ang\":{},\"z_ang\":{},\"x_acc\":{},\"y_acc\":{},\"elect\":{},\"z_acc\":{}},\"name\":\"???????????????3\"},{\"modelName\":\"???????????????\"," +
            "\"uniqueCode\":\"100000071\",\"dataItems\":[{\"itemName\":\"X?????????\",\"unit\":\"??\",\"dataId\":\"x_ang\",\"id\":1},{\"itemName\":\"Y?????????\",\"unit\":\"??\",\"dataId\":\"y_ang\"," +
            "\"id\":2},{\"itemName\":\"Z?????????\",\"unit\":\"??\",\"dataId\":\"z_ang\",\"id\":3},{\"itemName\":\"X????????????\",\"unit\":\"g\",\"dataId\":\"x_acc\",\"id\":4},{\"itemName\":" +
            "\"Y????????????\",\"unit\":\"g\",\"dataId\":\"y_acc\",\"id\":5},{\"itemName\":\"Z????????????\",\"unit\":\"g\",\"dataId\":\"z_acc\",\"id\":6},{\"itemName\":\"??????\",\"dataId\":" +
            "\"elect\",\"id\":7},{\"itemName\":\"??????\",\"unit\":\"??C \",\"dataId\":\"temp\",\"id\":8}],\"dataMap\":{\"y_ang\":{},\"temp\":{},\"x_ang\":{},\"z_ang\":{},\"x_acc\":{}," +
            "\"y_acc\":{},\"elect\":{},\"z_acc\":{}},\"name\":\"???????????????4\"},{\"modelName\":\"???????????????\",\"uniqueCode\":\"100000008\",\"dataItems\":[{\"itemName\":\"X?????????\",\"unit\":" +
            "\"??\",\"dataId\":\"x_ang\",\"id\":1},{\"itemName\":\"Y?????????\",\"unit\":\"??\",\"dataId\":\"y_ang\",\"id\":2},{\"itemName\":\"Z?????????\",\"unit\":\"??\",\"dataId\":\"z_ang\"," +
            "\"id\":3},{\"itemName\":\"X????????????\",\"unit\":\"g\",\"dataId\":\"x_acc\",\"id\":4},{\"itemName\":\"Y????????????\",\"unit\":\"g\",\"dataId\":\"y_acc\",\"id\":5}," +
            "{\"itemName\":\"Z????????????\",\"unit\":\"g\",\"dataId\":\"z_acc\",\"id\":6},{\"itemName\":\"??????\",\"dataId\":\"elect\",\"id\":7},{\"itemName\":\"??????\",\"unit\":\"??C \"," +
            "\"dataId\":\"temp\",\"id\":8}],\"dataMap\":{\"y_ang\":{},\"temp\":{},\"x_ang\":{},\"z_ang\":{},\"x_acc\":{},\"y_acc\":{},\"elect\":{},\"z_acc\":{}},\"name\":\"????????????\"}," +
            "{\"modelName\":\"???????????????\",\"uniqueCode\":\"999999999\",\"dataItems\":[{\"itemName\":\"X?????????\",\"unit\":\"??\",\"dataId\":\"x_ang\",\"id\":1},{\"itemName\":\"Y?????????\"," +
            "\"unit\":\"??\",\"dataId\":\"y_ang\",\"id\":2},{\"itemName\":\"Z?????????\",\"unit\":\"??\",\"dataId\":\"z_ang\",\"id\":3},{\"itemName\":\"X????????????\",\"unit\":\"g\",\"dataId\":" +
            "\"x_acc\",\"id\":4},{\"itemName\":\"Y????????????\",\"unit\":\"g\",\"dataId\":\"y_acc\",\"id\":5},{\"itemName\":\"Z????????????\",\"unit\":\"g\",\"dataId\":\"z_acc\",\"id\":6}," +
            "{\"itemName\":\"??????\",\"dataId\":\"elect\",\"id\":7},{\"itemName\":\"??????\",\"unit\":\"??C \",\"dataId\":\"temp\",\"id\":8}],\"dataMap\":{\"y_ang\":{},\"temp\":{},\"x_ang\":{}," +
            "\"z_ang\":{},\"x_acc\":{},\"y_acc\":{},\"elect\":{},\"z_acc\":{}},\"name\":\"????????????????????????\"}]";

    private static final String SIGN_DATA = "www.bwsensing.com";



    @Test
    public void testDataRsa() throws Exception {
        System.out.println(new String(RSAUtils.decryptByPrivateKey(ENCRYPT_HEX_STRING, TEST_PRIVATE_KEY)));
        Assert.assertEquals(new String(RSAUtils.decryptByPrivateKey(ENCRYPT_HEX_STRING, TEST_PRIVATE_KEY)),EXPECT_DATA_RESULT);
    }


    @Test
    public void testSignatureRsa() throws Exception {
        byte[] data = SIGN_DATA.getBytes();
        System.out.println(HexUtils.bytesToHexString(data));
        System.out.println(RSAUtils.sign(data,TEST_PRIVATE_KEY));
    }

}
