# ML-Kit-Android-Example 📱🔍

Ứng dụng Android demo các tính năng của thư viện ML Kit, xây dựng bằng Kotlin và Jetpack Compose

## 🌟 Tính năng nổi bật
- **Quét mã vạch/QR code**  
  Nhận diện được 13 định dạng mã vạch trực tiếp từ camera
- **Gán label hình ảnh**  
  Tự động phát hiện và phân loại đối tượng trong ảnh / camera
- **Dịch văn bản**  
  Dịch Anh-Việt offline sau khi tải model ngôn ngữ

## 🛠 Công nghệ sử dụng
### Kiến trúc
- **Clean Architecture** (Data - Domain - Presentation)
- **MVVM** kết hợp với **Jetpack Compose**
- **Dependency Injection** với Hilt

### Thư viện chính
| Category       | Libraries/Frameworks                                    |
|----------------|---------------------------------------------------------|
| UI            | Jetpack Compose, Material 3       |
| Camera        | CameraX (Preview + Analysis)                            |
| ML            | ML Kit (Barcode, Image Labeling, Translation)           |
| Async         | Kotlin Coroutines, Flow                                 |
| DI            | Hilt                                                    | |
| Navigation    | Compose Navigation                                      |
