<!-- Turkish Content -->
<details>
  <summary>Türkçe</summary>
<details>
  <summary>Sınıflar Göster</summary>

### ContentService Sınıfı Metodları

<details>

## 1. `createNewContent`

  <summary>Metodları Göster</summary>

<details>
  <summary>Açıklamaları Göster</summary>

**Açıklama:**  
Bu metot, yeni bir içerik oluşturur. İçerik başlığı, açıklaması gibi temel bilgileri alır ve veri tabanına kaydeder.

### Parametreler

- **CreateNewContentRequest**: İçeriğin başlığı ve açıklaması bilgilerini içerir.
- **contentTitle**: İçerik başlığı (String).
- **contentDescription**: İçeriğin açıklaması veya uzun metin (String).

### Dönüş Değeri

- **ApiResponse<ContentResponse>**: İçeriğin temel düzeyde oluşturulmuş halini döner.

---

### Örnek Dönüş Verisi

#### `ContentResponse`:

```json
{
  "contentTitle": "string",
  "contentDescription": "string"
}
```

#### `ApiResponse`:

```json
{
  "status": true,
  "message": "string",
  "data": {
    "contentTitle": "string",
    "contentDescription": "string"
  }
}
```

</details>
</details>
</details>
</details>

<!-- Turkish Content End -->

-- CHOISE UR LANG.

<!-- English Content -->
<details>
  <summary>English</summary>
<details>
  <summary>Show Class</summary>

### ContentService Class Functions

<details>

## 1. `createNewContent`

<summary>Show Methods</summary>

<details>
  <summary>Show Descriptions</summary>

**Description:**  
This method creates a new content. It takes basic information such as the content title and description and saves it to the database.

### Parameters

- **CreateNewContentRequest**: Contains the content's title and description information.
- **contentTitle**: The title of the content (String).
- **contentDescription**: The description or detailed text of the content (String).

### Return Value

- **ApiResponse<ContentResponse>**: Returns the basic created version of the content.

---

### Örnek Dönüş Verisi

#### `ContentResponse`:

```json
{
  "contentTitle": "string",
  "contentDescription": "string"
}
```

#### `ApiResponse`:

```json
{
  "status": true,
  "message": "string",
  "data": {
    "contentTitle": "string",
    "contentDescription": "string"
  }
}
```

</details>
</details>
</details>
</details>
<!-- English Content End-->
