
      package example; import org.apache.commons.lang.WordUtils; public class Hello { public static void main(String[] args) { String message = "hello ivy !"; System.out.println("standard message : " + message); System.out.println("capitalized by " + WordUtils.class.getName() + " : " + WordUtils.capitalizeFully(message)); } }
    