# recruitment-task

1. Wybór drivera następuję w klasie BaseTest przy użyciu wzorca Factory
2. Testy uruchamiamy komendą mvn clean test site
3. Raport z testów odkłada się w /recruitment-task/target/surefire-reports/index.html (zalecam przeglądanie bezpośrednio z exploratora plików)
4. Pozwoliłem sobie na nie powielaniu tych samych operacji w testach API dla każdej encji ponieważ logika jest niezmienna
5. Nieprzechodzenie testu removeProductFromCart jest poprawne
