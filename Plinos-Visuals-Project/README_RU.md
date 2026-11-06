# Plinos Visuals — исходный проект

**Цель:** Fabric client mod для Minecraft Java 1.21.11, Java 21, SKLauncher.

## Управление
- `Right Shift` — открыть/закрыть ClickGUI.
- `Left Alt` — переключить флаг Cinematic Camera (пока это только переключатель; сглаживание мыши ещё не реализовано).

## Сборка через GitHub Actions
1. Создай/открой репозиторий GitHub.
2. Загрузи содержимое этой папки в корень репозитория, сохранив структуру каталогов.
3. Создай `.github/workflows/build.yml` со следующим содержимым:

```yaml
name: Build Plinos Visuals
on:
  workflow_dispatch:
  push:
    branches: [ main ]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-java@v4
        with:
          distribution: microsoft
          java-version: '21'
      - uses: gradle/actions/setup-gradle@v4
      - name: Build
        run: gradle build
      - uses: actions/upload-artifact@v4
        with:
          name: Plinos-Visuals
          path: build/libs/*.jar
```

4. В GitHub открой **Actions** → **Build Plinos Visuals** → **Run workflow**.
5. После успешной сборки скачай artifact `Plinos-Visuals`.
6. Распакуй ZIP artifact и возьми `plinos-visual-1.0.0.jar` (не `-sources.jar`).
7. Закрой Minecraft. Положи JAR в папку `.minecraft/mods`.
8. Запусти в SKLauncher профиль Minecraft 1.21.11 с Fabric Loader и Fabric API.

## Важное состояние функций
Этот архив — стартовый исходный проект и GUI-каркас, а не готовая реализация всех перечисленных эффектов. Кнопки переключают настройки, но следующие функции ещё требуют отдельной реализации и тестирования:
- Custom Glint / Item Glow и выбор цвета/градиента.
- Реальные Hitmarkers, частицы, урон-эффекты, HitSounds и KillSounds.
- Реальное сглаживание Cinematic Camera, Motion Blur, Color Correction и Green Screen.
- HUD: прочность брони, ping, таймер и кастомный прицел.
- Streamer Mode: реальное скрытие никнеймов в рендере.

## SKLauncher / Account Manager
SKLauncher выполняет Microsoft/offline авторизацию до запуска Minecraft. Обычный Fabric-мод не должен перехватывать пароль лаунчера или подменять токены. Поэтому в GUI оставлена информационная страница: добавление и переключение launcher-аккаунтов нужно делать через сам SKLauncher. Это не закрывает Minecraft-модом и не требует хранить пароль в моде.

## Маскировка `/login` и `/register`
В проекте пока нет готового перехватчика чата. Не вводи настоящие пароли во время записи/стрима до появления и проверки такой функции. Корректная реализация должна скрывать текст локально на экране/в истории чата, но отправлять исходную команду серверу без изменений; это нужно отдельно проверить на сервере авторизации.

## Сборка
Требуются интернет-доступ для загрузки Gradle/Fabric зависимостей и Java 21. Если сборка падает, пришли первые ошибки из GitHub Actions (начиная с `error:`).
