Настройка параметров симуляции в директории scr/main/resources/ Настраиваемые параметры:

- Настройка размера карты, времени такта, количества травы за такт и т.д.: islandconfig.properties
- Вероятность поедания одного животного другим: animaleatprobability.properties
- Максимальное количество шагов животного за один такт: stepmap.properties
- Максимальное количество здоровья у животных: maxhealth.properties
- Начальная популяция животных при инициализации: animalstartpopulation.properties
- Максимальный возраст каждого вида животного в тактах: animalmaxage.properties


Реализовано:

- Случайное заселение популяции острова
- Настройки выведены в отдельные файлы
- Вывод статистики каждый такт, карта ячеек, количество каждого вида животных, среднее количество травы в каждой ячейке, количество всех животных
- Животные размножаются, выбирая своего партнера из списка. Размножение ограничено: не в каждом такте можно размножиться, размножиться с самим собой нельзя. Оба родителя на время не могут размножаться
- Популяция животных контролируется ограничением продолжительности жизни каждой особи, умирают от старости либо от голода
- Отдельный Thread для генерации травы в каждую ячейку


Для добавления нового животного:

- Завести класс, наследоваться от хищника/травоядного, настроить toString и несколько методов по аналогии с другими животными.
- В конфигурационных файлах добавить вероятность съесть/быть съеденным, максимальное здоровье, шаги за такт, начальная популяция животного
- В AnimalFactory добавить if class instanceOf
- В LoadClass в статик блоке добавить в animalClassMap по аналогии с другими животными мэппинг короткого названия с полным путем до класса.class