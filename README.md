# RecipeFinder

RecipeFinder, kullanıcıların tarifleri aramasına, görüntülemesine ve favori tariflerini kaydetmesine yardımcı olan bir Android uygulamasıdır. Uygulama, ağ çağrıları için Retrofit, yerel veritabanı depolaması için Room ve kullanıcı arayüzü için Jetpack Compose kullanır. Ayrıca bağımlılık enjeksiyonu için Hilt kullanır.

---

## Özellikler

- **Tarif Arama:** Spoonacular API kullanarak tarif arama.
- **Tarif Detayları Görüntüleme:** Seçilen tariflerin detaylı bilgilerini görüntüleme.
- **Favori Tarifleri Kaydetme:** Tarifleri favori olarak işaretleyip çevrimdışı erişim sağlama.
- **Tarif Talimatları:** Tariflerin adım adım talimatlarını görüntüleme.
- **Çevrimdışı Erişim:** İnternet bağlantısı olmadan kaydedilen favori tariflere erişim.

---

## Ekranlar

1. **Arama Ekranı:** Kullanıcıların tarif aramasını sağlar.
2. **Favori Ekranı:** Kullanıcının kaydettiği favori tariflerin listesini görüntüler.
3. **Tarif Detay Ekranı:** Seçilen tarif hakkında detaylı bilgi sunar.

---

## Mimari

Uygulama MVVM (Model-View-ViewModel) mimarisini takip eder.

- **Model:** Veri sınıflarını ve depo sınıflarını içerir.
- **View:** Kullanıcı arayüzü için Compose bileşenlerini içerir.
- **ViewModel:** UI ile ilgili verileri ve iş mantığını yönetir.

---

## Kütüphaneler ve Araçlar

- **Retrofit:** Ağ işlemleri için.
- **Room:** Yerel veritabanı depolaması için.
- **Hilt:** Bağımlılık enjeksiyonu için.
- **Jetpack Compose:** Kullanıcı arayüzü oluşturmak için.
- **Coil:** Görselleri yüklemek için.
- **Navigation Component:** Uygulama navigasyonunu yönetmek için.

---

## Başlarken

### Gereksinimler

- Android Studio Flamingo veya daha yeni bir sürüm
- Android SDK 21 veya daha yüksek
- Tarifleri API'den çekmek için internet bağlantısı

### Kurulum

1. Depoyu klonlayın:
    ```bash
    git clone https://github.com/kullaniciadi/recipefinder.git
    ```

2. Projeyi Android Studio'da açın.

3. Spoonacular API anahtarınızı `Constant.kt` dosyasına ekleyin (Uygulama içerisinde bir API_KEY bulunmaktadır. eğer isterseniz * [**Spoonacular.com**](https://spoonacular.com/food-api) adresinden kendi keyinizi alabilirsiniz.):
    ```kotlin
    package com.example.recipefinder.utils

    object Constant {
        const val API_KEY = "YOUR_SPOONACULAR_API_KEY"
    }
    ```

4. Projeyi derleyin ve çalıştırın.

---

## Kod Yapısı

### Veri Katmanı

- **Remote:** API servis arayüzlerini ve ağ modellerini içerir.
  - `api/RecipeApi.kt`
  - `model/RecipeResponse.kt`
  - `model/RecipeDetailModel.kt`

- **Local:** Room veritabanı varlıkları, DAO'lar ve veritabanı yapılandırmasını içerir.
  - `dao/RecipeDao.kt`
  - `entities/RecipeEntity.kt`
  - `entities/RecipeDetailEntity.kt`

- **Repository:** Veri işlemlerini yönetir ve tek bir kaynak olarak hareket eder.
  - `repository/RecipeRepository.kt`

### Görünüm Katmanı

- **Compose Bileşenleri:** Jetpack Compose kullanarak UI bileşenlerini tanımlar.
  - `feature/home/HomeScreen.kt`
  - `feature/search/SearchScreen.kt`
  - `feature/favorite/FavoriteScreen.kt`
  - `feature/recipedetail/RecipeDetailScreen.kt`

### ViewModel Katmanı

- **ViewModel'ler:** UI ile ilgili verileri ve iş mantığını yönetir.
  - `feature/home/HomeViewModel.kt`
  - `feature/search/SearchViewModel.kt`
  - `feature/favorite/FavoriteViewModel.kt`
  - `feature/recipedetail/RecipeDetailViewModel.kt`

### Navigasyon

- **Navigasyon:** Farklı ekranlar arasında navigasyonu yönetir.
  - `navigation/RecipeFinderNavGraph.kt`

---

## Kullanım

### Tarif Detaylarını Görüntüleme

1. Herhangi bir listeden (Ana Sayfa, Arama veya Favoriler) bir tarife dokunun.
2. Malzemeler ve talimatlar dahil olmak üzere detaylı bilgileri görüntüleyin.

### Favori Tarifleri Kaydetme

1. Bir tarifi favori olarak işaretlemek için kalp simgesine dokunun.
2. Favori tariflerinize Favoriler ekranından erişin.

### Favori Tarifleri Kaldırma

1. Favoriler ekranına gidin veya Ana ekrana gidin.
2. Tarifin kalp simgesine tekrar dokunarak tarifi favorilerden çıkarın.

---

## Teşekkürler

- Tarif verilerini sağladığı için Spoonacular API.
- Kullanıcı arayüzü geliştirmeyi kolay ve keyifli hale getiren Jetpack Compose.
- Bu projede kullanılan tüm açık kaynak kütüphaneler.

---

**Afiyet olsun!**
