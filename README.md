# NewsApplication

Bu Android uygulaması, [NewsAPI.org](https://newsapi.org/) kullanarak güncel haber başlıklarını listeleyen basit bir haber okuma uygulamasıdır. Kullanıcılar haber başlıklarını görebilir ve tıkladıkları haberleri bir WebView içerisinde okuyabilirler.

## Özellikler

*   Güncel haber başlıklarını listeleme (ABD'den en popüler haberler).
*   Haber başlığına tıklandığında haberi uygulama içi WebView'da açma.
*   Haberler yüklenirken ilerleme çubuğu gösterme.
*   API'den veri alınamadığında veya hata oluştuğunda kullanıcıya bilgi verme.

*   ## Kullanılan Teknolojiler ve Kütüphaneler

*   **Kotlin:** Ana programlama dili.
*   **Android SDK:** Temel Android geliştirme araçları.
*   **Retrofit:** HTTP istekleri için (API çağrıları).
*   **Gson Converter:** JSON verilerini Kotlin objelerine dönüştürmek için.
*   **OkHttp Logging Interceptor:** Ağ isteklerini loglamak için.
*   **Glide:** Resim yükleme ve önbelleğe alma (şimdiki versiyonda haber resimleri gösterilmiyor ama ileride eklenebilir).
*   **RecyclerView:** Haber listesini verimli bir şekilde göstermek için.
*   **WebView:** Haber detaylarını göstermek için.
*   **ViewBinding:** Layout dosyalarındaki view'lara daha kolay erişim için.
*   **Coroutines (Kotlinx):** Asenkron işlemler için (şimdiki versiyonda callback'ler kullanılıyor).

## Gelecekteki Geliştirmeler (İsteğe Bağlı)

*   Farklı ülkelerden veya kategorilerden haber seçebilme.
*   Arama özelliği.
*   Haberleri favorilere ekleme.
*   Karanlık mod desteği.
*   Daha gelişmiş bir UI/UX.
