
> ⚠️ This project is still in beta and may have errors!  
⚠️ If you found any error or bug, please open [issue](https://github.com/q1zZu/spaceis-sdk-java/issues) or contact with me via [discord](https://bit.ly/Q-CODES) *(open ticket)*.

# spaceis-sdk-java
Java sdk for [SpaceIs](https://spaceis.pl) e-commerce platform.


## **Supported endpoints:**
- [License](https://api.spaceis.pl/#tag/Licencja)
- [Daily reward](https://api.spaceis.pl/#tag/Dzienna-nagroda)
- [Vouchers](https://api.spaceis.pl/#tag/Vouchery)
- [Subpages](https://api.spaceis.pl/#tag/Podstrony)
- [Discount code](https://api.spaceis.pl/#tag/Kody-rabatowe)
- [Server commands](https://api.spaceis.pl/#tag/Komendy-dla-serwera)
- [Transactions](https://api.spaceis.pl/#tag/Transakcje)
- [Servers](https://api.spaceis.pl/#tag/Serwery)

# Installation

[![](https://jitpack.io/v/q1zZu/spaceis-sdk-java.svg)](https://jitpack.io/#q1zZu/spaceis-sdk-java)

**Maven**
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

```xml
<dependency>
    <groupId>com.github.q1zzu</groupId>
    <artifactId>spaceis-sdk-java</artifactId>
    <version>{VERSION}</version>
</dependency>
```

**Gradle**
```groovy
maven { url 'https://jitpack.io' }
```

```groovy
implementation 'com.github.q1zzu:spaceis-sdk-java:{VERSION}'
```

> Replace `{VERSION}` with latest version from [releases](https://github.com/q1zZu/spaceis-sdk-java/releases) or [jitpack](https://jitpack.io/#q1zZu/spaceis-sdk-java)

# SpaceIsSdkClient

**Client initalization**
```java

// Client with api key
final SpaceIsSdk spaceIsSdkClient = new SpaceIsSdkClient(API_KEY);

// Client with base url and api key.
final SpaceIsSdk spaceIsSdkClient = new SpaceIsSdkClient(BASE_URL, API_KEY);

```

> Examples for usage can be found at: [wiki](https://github.com/q1zZu/spaceis-sdk-java/wiki)

# Support and contact
- Discord: [Q-CODES](https://bit.ly/Q-CODES) *(create ticket)*
- Email: q1zZ@zetcraft.pl




