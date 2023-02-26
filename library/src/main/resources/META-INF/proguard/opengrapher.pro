-keep class org.apache.** { *; }

-keep public class io.ktor.client.call.TypeBase {
    public <methods>;
    private <methods>;
}
