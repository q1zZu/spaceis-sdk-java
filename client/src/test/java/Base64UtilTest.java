import me.q1zz.spaceis.sdk.util.Base64Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base64UtilTest {

    @Test
    public void encodingTest() {
        Assertions.assertEquals("test123!@#", Base64Util.decode("dGVzdDEyMyFAIw=="));
    }

}
