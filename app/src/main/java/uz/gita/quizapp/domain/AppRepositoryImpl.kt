package uz.gita.quizapp.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.gita.quizapp.R
import uz.gita.quizapp.data.model.QuestionModel
import uz.gita.quizapp.data.model.QuestionResultModel
import uz.gita.quizapp.data.model.QuizData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl
@Inject constructor() : AppRepository {

    private var lastCorrectsCount = 0
    private var finishedQuestionsCount = 0
    private var lastResult = ArrayList<QuestionResultModel>()
    private lateinit var quizData: QuizData

    override fun getAllQuizzes(): Flow<Result<List<QuizData>>> = flow {
        emit(Result.success(database))
    }

    override fun saveCurrentQuizResult(
        resultList: List<QuestionResultModel>,
        correctAnswers: Int,
        finishedQuestions: Int
    ) {
        lastResult.clear()
        lastResult.addAll(resultList)
        lastCorrectsCount = correctAnswers
        finishedQuestionsCount = finishedQuestions
    }

    override fun saveQuizData(data: QuizData) {
        quizData = data
    }

    override fun getResultData(): List<QuestionResultModel> = lastResult
    override fun getQuizData(): QuizData = quizData
    override fun getResultPercentage() = (lastCorrectsCount * 100F / lastResult.size).toInt()
    override fun getCorrectAnswersCount() = lastCorrectsCount
    override fun getFinishedQuestionsCount() = finishedQuestionsCount

    private val database = listOf(
        QuizData(
            id = "1",
            imgResID = R.drawable.img_europe ,
            title = "Europe",
            description = "Test your knowledge about Europe",
            time = "2",
            questionsArr = arrayListOf(
                QuestionModel(
                    "What is the capital city of France?",
                    listOf("Berlin", "Madrid", "Paris", "Rome"),
                    "Paris"
                ),
                QuestionModel(
                    "Which country is famous for its windmills, tulips, and bicycles?",
                    listOf("Netherlands", "Belgium", "Germany", "Switzerland"),
                    "Netherlands"
                ),
                QuestionModel(
                    "Which city is known for its canals and gondolas?",
                    listOf("Venice", "Athens", "Dublin", "Prague"),
                    "Venice"
                ),
                QuestionModel(
                    "What is the tallest mountain in Europe?",
                    listOf("Mount Everest", "Mont Blanc", "Mount Elbrus", "Matterhorn"),
                    "Mount Elbrus"
                ),
                QuestionModel(
                    "Which city is the capital of the United Kingdom?",
                    listOf("Paris", "London", "Berlin", "Rome"),
                    "London"
                ),
                QuestionModel(
                    "Which country is famous for its delicious chocolate and waffles?",
                    listOf("Italy", "France", "Belgium", "Switzerland"),
                    "Belgium"
                ),
                QuestionModel(
                    "What is the longest river in Europe?",
                    listOf("Thames", "Rhine", "Danube", "Volga"),
                    "Volga"
                ),
                QuestionModel(
                    "Which country is home to the Acropolis and Parthenon?",
                    listOf("Italy", "Greece", "Spain", "Turkey"),
                    "Greece"
                ),
                QuestionModel(
                    "What is the official currency of Germany?",
                    listOf("Euro", "Pound Sterling", "Dollar", "Yen"),
                    "Euro"
                ),
                QuestionModel(
                    "Which city is known as the 'City of Love'?",
                    listOf("London", "Paris", "Rome", "Vienna"),
                    "Paris"
                )
            )
        ),
        QuizData(
            "2",
            imgResID = R.drawable.img_australia,
            "Australia",
            "Test your knowledge about Australia",
            "2",
            arrayListOf(
                QuestionModel(
                    "What is the capital city of Australia?",
                    listOf("Sydney", "Melbourne", "Canberra", "Brisbane"),
                    "Canberra"
                ),
                QuestionModel(
                    "Which famous natural landmark in Australia is known for its red sandstone formations?",
                    listOf("Uluru", "Great Barrier Reef", "Sydney Opera House", "Blue Mountains"),
                    "Uluru"
                ),
                QuestionModel(
                    "What is the largest city in Australia by population?",
                    listOf("Sydney", "Melbourne", "Perth", "Brisbane"),
                    "Sydney"
                ),
                QuestionModel(
                    "Which animal is commonly associated with Australia and can be found in pouches?",
                    listOf("Kangaroo", "Koala", "Emu", "Platypus"),
                    "Kangaroo"
                ),
                QuestionModel(
                    "Which famous structure in Sydney is known for its unique sail-like design?",
                    listOf("Sydney Harbour Bridge", "Great Ocean Road", "Bondi Beach", "Sydney Opera House"),
                    "Sydney Opera House"
                ),
                QuestionModel(
                    "Which Australian city is famous for its annual horse race, the Melbourne Cup?",
                    listOf("Melbourne", "Sydney", "Brisbane", "Adelaide"),
                    "Melbourne"
                ),
                QuestionModel(
                    "What is the name of the famous coral reef system located off the coast of Queensland, Australia?",
                    listOf("Great Barrier Reef", "Coral Sea Reef", "Tasmanian Reef", "Cairns Reef"),
                    "Great Barrier Reef"
                ),
                QuestionModel(
                    "What is the nickname commonly used for Australia?",
                    listOf(
                        "The Land Down Under",
                        "The Land of the Rising Sun",
                        "The Great Outback",
                        "The Emerald Isle"
                    ),
                    "The Land Down Under"
                ),
                QuestionModel(
                    "Which Australian city hosted the 2000 Summer Olympics?",
                    listOf("Melbourne", "Sydney", "Brisbane", "Perth"),
                    "Sydney"
                ),
                QuestionModel(
                    "What is the largest state/territory by area in Australia?",
                    listOf("New South Wales", "Queensland", "Western Australia", "Victoria"),
                    "Western Australia"
                )
            )
        ),
        QuizData(
            "3",
            imgResID = R.drawable.img_africa,
            "Africa",
            "Test your knowledge about Africa",
            "2",
            arrayListOf(
                QuestionModel(
                    "What is the largest desert in Africa?",
                    listOf("Sahara Desert", "Kalahari Desert", "Namib Desert", "Atacama Desert"),
                    "Sahara Desert"
                ),
                QuestionModel(
                    "Which river is the longest in Africa?",
                    listOf("Amazon River", "Nile River", "Congo River", "Zambezi River"),
                    "Nile River"
                ),
                QuestionModel(
                    "Which animal is known as the 'King of the Jungle'?",
                    listOf("Elephant", "Giraffe", "Lion", "Hippo"),
                    "Lion"
                ),
                QuestionModel(
                    "What is the capital city of South Africa?",
                    listOf("Nairobi", "Cairo", "Johannesburg", "Pretoria"),
                    "Pretoria"
                ),
                QuestionModel(
                    "Which country is known as the 'Land of a Thousand Hills'?",
                    listOf("Kenya", "Tanzania", "Uganda", "Rwanda"),
                    "Rwanda"
                ),
                QuestionModel(
                    "What is the highest mountain in Africa?",
                    listOf("Mount Kilimanjaro", "Mount Kenya", "Mount Elgon", "Mount Cameroon"),
                    "Mount Kilimanjaro"
                ),
                QuestionModel(
                    "Which African country is known as the 'Giant of Africa'?",
                    listOf("Nigeria", "Ethiopia", "South Africa", "Egypt"),
                    "Nigeria"
                ),
                QuestionModel(
                    "What is the currency of Egypt?",
                    listOf("Peso", "Dinar", "Rand", "Pound"),
                    "Pound"
                ),
                QuestionModel(
                    "Which lake is the largest in Africa by surface area?",
                    listOf("Lake Victoria", "Lake Tanganyika", "Lake Malawi", "Lake Chad"),
                    "Lake Victoria"
                ),
                QuestionModel(
                    "What is the name of the largest waterfall in Africa?",
                    listOf("Victoria Falls", "Niagara Falls", "Iguazu Falls", "Angel Falls"),
                    "Victoria Falls"
                )
            )
        ),
        QuizData(
            "4",
            imgResID = R.drawable.img_north_america,
            "North America",
            "Test your knowledge about North America",
            "2",
            arrayListOf(
                QuestionModel(
                    "Which country has the largest land area in North America?",
                    listOf("Canada", "United States", "Mexico", "Greenland"),
                    "Canada"
                ),
                QuestionModel(
                    "What is the capital city of the United States?",
                    listOf("Washington D.C.", "New York City", "Los Angeles", "Chicago"),
                    "Washington D.C."
                ),
                QuestionModel(
                    "Which Canadian city is famous for its maple syrup and hockey?",
                    listOf("Toronto", "Montreal", "Vancouver", "Calgary"),
                    "Montreal"
                ),
                QuestionModel(
                    "Which state is known as the 'Sunshine State'?",
                    listOf("California", "Texas", "Florida", "Hawaii"),
                    "Florida"
                ),
                QuestionModel(
                    "What is the longest river in North America?",
                    listOf("Mississippi River", "Yukon River", "Rio Grande", "Colorado River"),
                    "Mississippi River"
                ),
                QuestionModel(
                    "Which mountain range runs along the western coast of North America?",
                    listOf("Rocky Mountains", "Appalachian Mountains", "Andes Mountains", "Sierra Nevada"),
                    "Rocky Mountains"
                ),
                QuestionModel(
                    "What is the official language of Mexico?",
                    listOf("Spanish", "English", "French", "Portuguese"),
                    "Spanish"
                ),
                QuestionModel(
                    "Which city is known as the 'Big Apple'?",
                    listOf("Los Angeles", "Chicago", "New York City", "Miami"),
                    "New York City"
                ),
                QuestionModel(
                    "What is the largest lake in North America?",
                    listOf("Lake Superior", "Great Bear Lake", "Great Slave Lake", "Lake Michigan"),
                    "Lake Superior"
                ),
                QuestionModel(
                    "Which island country is part of North America?",
                    listOf("Haiti", "Cuba", "Jamaica", "Dominican Republic"),
                    "Cuba"
                )
            )
        ),
        QuizData(
            "5",
            imgResID = R.drawable.img_south_america,
            "South America",
            "Test your knowledge about South America",
            "2",
            arrayListOf(
                QuestionModel(
                    "Which river is the longest in South America?",
                    listOf("Amazon River", "Nile River", "Parana River", "Orinoco River"),
                    "Amazon River"
                ),
                QuestionModel(
                    "What is the capital city of Brazil?",
                    listOf("Sao Paulo", "Rio de Janeiro", "Brasilia", "Buenos Aires"),
                    "Brasilia"
                ),
                QuestionModel(
                    "Which mountain range runs along the western coast of South America?",
                    listOf("Rocky Mountains", "Andes Mountains", "Appalachian Mountains", "Sierra Nevada"),
                    "Andes Mountains"
                ),
                QuestionModel(
                    "Which South American country is known for the tango?",
                    listOf("Argentina", "Chile", "Peru", "Colombia"),
                    "Argentina"
                ),
                QuestionModel(
                    "What is the largest country in South America by land area?",
                    listOf("Brazil", "Argentina", "Peru", "Colombia"),
                    "Brazil"
                ),
                QuestionModel(
                    "Which city is known as the 'Paris of South America'?",
                    listOf("Buenos Aires", "Lima", "Santiago", "Caracas"),
                    "Buenos Aires"
                ),
                QuestionModel(
                    "What is the official language of Brazil?",
                    listOf("Spanish", "Portuguese", "English", "French"),
                    "Portuguese"
                ),
                QuestionModel(
                    "Which South American country has the highest capital city in elevation?",
                    listOf("Peru", "Colombia", "Bolivia", "Ecuador"),
                    "Bolivia"
                ),
                QuestionModel(
                    "What is the largest rainforest in the world located in South America?",
                    listOf("Congo Rainforest", "Daintree Rainforest", "Amazon Rainforest", "Borneo Rainforest"),
                    "Amazon Rainforest"
                ),
                QuestionModel(
                    "Which country is known for its colorful carnival celebrations, including in Rio de Janeiro?",
                    listOf("Colombia", "Peru", "Brazil", "Chile"),
                    "Brazil"
                )
            )
        ),
        QuizData(
            "6",
            imgResID = R.drawable.img_asia,
            "Asia",
            "Test your knowledge about Asia",
            "2",
            arrayListOf(
                QuestionModel(
                    "Which continent is the largest by land area?",
                    listOf("Africa", "Asia", "Europe", "North America"),
                    "Asia"
                ),
                QuestionModel(
                    "What is the capital city of Japan?",
                    listOf("Beijing", "Seoul", "Tokyo", "Bangkok"),
                    "Tokyo"
                ),
                QuestionModel(
                    "Which Asian country is known as the 'Land of the Rising Sun'?",
                    listOf("China", "South Korea", "Japan", "Thailand"),
                    "Japan"
                ),
                QuestionModel(
                    "What is the tallest mountain in the world located in Asia?",
                    listOf("Mount Everest", "K2", "Kangchenjunga", "Makalu"),
                    "Mount Everest"
                ),
                QuestionModel(
                    "Which river is considered the cradle of civilization in Asia?",
                    listOf("Yangtze River", "Mekong River", "Indus River", "Ganges River"),
                    "Indus River"
                ),
                QuestionModel(
                    "Which country is known as the 'Land of Smiles'?",
                    listOf("India", "China", "Thailand", "Vietnam"),
                    "Thailand"
                ),
                QuestionModel(
                    "What is the largest desert in Asia?",
                    listOf("Sahara Desert", "Gobi Desert", "Arabian Desert", "Thar Desert"),
                    "Gobi Desert"
                ),
                QuestionModel(
                    "Which Asian city is famous for its skyline featuring the Petronas Twin Towers?",
                    listOf("Tokyo", "Shanghai", "Singapore", "Kuala Lumpur"),
                    "Kuala Lumpur"
                ),
                QuestionModel(
                    "What is the official language of China?",
                    listOf("Mandarin", "Cantonese", "Shanghainese", "Hokkien"),
                    "Mandarin"
                ),
                QuestionModel(
                    "Which country is known for its delicious sushi and sashimi dishes?",
                    listOf("South Korea", "Thailand", "Vietnam", "Japan"),
                    "Japan"
                )
            )
        ),

        QuizData(
            "7",
            imgResID = R.drawable.img_arctica_and_antarctica,
            "Arctic and Antarctic Regions",
            "Test your knowledge about the Arctic and Antarctic regions",
            "2",
            arrayListOf(
                QuestionModel(
                    "Which region is located around the North Pole?",
                    listOf("Arctic", "Antarctic", "Asia", "Europe"),
                    "Arctic"
                ),
                QuestionModel(
                    "Which region is located around the South Pole?",
                    listOf("Arctic", "Antarctic", "Asia", "Europe"),
                    "Antarctic"
                ),
                QuestionModel(
                    "What is the largest landmass in the Arctic region?",
                    listOf("Greenland", "Alaska", "Canada", "Russia"),
                    "Greenland"
                ),
                QuestionModel(
                    "What is the largest landmass in the Antarctic region?",
                    listOf("Antarctica", "Australia", "South America", "Africa"),
                    "Antarctica"
                ),
                QuestionModel(
                    "What is the name of the southernmost continent?",
                    listOf("Africa", "Antarctica", "Australia", "Europe"),
                    "Antarctica"
                ),
                QuestionModel(
                    "Which of the following animals is commonly found in the Arctic region?",
                    listOf("Polar bear", "Penguin", "Kangaroo", "Elephant"),
                    "Polar bear"
                ),
                QuestionModel(
                    "Which of the following animals is commonly found in the Antarctic region?",
                    listOf("Polar bear", "Penguin", "Kangaroo", "Elephant"),
                    "Penguin"
                ),
                QuestionModel(
                    "What is the name of the imaginary line that circles the Earth at approximately 66.5 degrees north latitude?",
                    listOf("Arctic Circle", "Equator", "Prime Meridian", "Tropic of Cancer"),
                    "Arctic Circle"
                ),
                QuestionModel(
                    "What is the name of the imaginary line that circles the Earth at approximately 66.5 degrees south latitude?",
                    listOf("Antarctic Circle", "Equator", "Prime Meridian", "Tropic of Capricorn"),
                    "Antarctic Circle"
                ),
                QuestionModel(
                    "What is the average temperature in the Arctic during the winter?",
                    listOf("-30°C to -40°C", "-10°C to 0°C", "10°C to 20°C", "30°C to 40°C"),
                    "-30°C to -40°C"
                )
            )
        ),
        QuizData(
            "8",
            imgResID = R.drawable.img_europe,
            "Europe - Hard Level",
            "Test your advanced knowledge about Europe",
            "4",
            arrayListOf(
                QuestionModel(
                    "What is the name of the mountain range that separates Europe from Asia?",
                    listOf("Alps", "Pyrenees", "Ural Mountains", "Caucasus Mountains"),
                    "Ural Mountains"
                ),
                QuestionModel(
                    "Which European country is known as the 'Land of Fire and Ice'?",
                    listOf("Iceland", "Norway", "Finland", "Switzerland"),
                    "Iceland"
                ),
                QuestionModel(
                    "What is the name of the strait that connects the Black Sea to the Aegean Sea?",
                    listOf("Strait of Gibraltar", "Strait of Hormuz", "Dardanelles", "Bosphorus"),
                    "Bosphorus"
                ),
                QuestionModel(
                    "Which city is known as the 'Pearl of the Danube'?",
                    listOf("Prague", "Budapest", "Vienna", "Bratislava"),
                    "Budapest"
                ),
                QuestionModel(
                    "Which European country has the highest population?",
                    listOf("Germany", "France", "United Kingdom", "Russia"),
                    "Germany"
                ),
                QuestionModel(
                    "What is the official currency of Switzerland?",
                    listOf("Euro", "Swiss franc", "Pound sterling", "Krone"),
                    "Swiss franc"
                ),
                QuestionModel(
                    "Which river is the longest in Europe?",
                    listOf("Danube", "Volga", "Rhine", "Seine"),
                    "Volga"
                ),
                QuestionModel(
                    "What is the name of the active volcano located in Italy?",
                    listOf("Vesuvius", "Etna", "Stromboli", "Vulcano"),
                    "Etna"
                ),
                QuestionModel(
                    "Which city is known as the 'City of a Hundred Spires'?",
                    listOf("Prague", "Warsaw", "Budapest", "Brussels"),
                    "Prague"
                ),
                QuestionModel(
                    "What is the name of the longest river in the Iberian Peninsula?",
                    listOf("Tagus", "Ebro", "Guadalquivir", "Douro"),
                    "Tagus"
                )
            )
        ),
        QuizData(
            "9",
            imgResID = R.drawable.img_australia,
            "Australia - Hard Level",
            "Test your advanced knowledge about Australia",
            "4",
            arrayListOf(
                QuestionModel(
                    "What is the name of the longest river in Australia?",
                    listOf("Murray River", "Darling River", "Yarra River", "Brisbane River"),
                    "Murray River"
                ),
                QuestionModel(
                    "Which Australian state or territory is the largest by land area?",
                    listOf("New South Wales", "Queensland", "Western Australia", "Northern Territory"),
                    "Western Australia"
                ),
                QuestionModel(
                    "What is the name of the mountain range that runs along the eastern coast of Australia?",
                    listOf("Great Dividing Range", "Australian Alps", "Blue Mountains", "Snowy Mountains"),
                    "Great Dividing Range"
                ),
                QuestionModel(
                    "Which city is known as the 'City of Churches'?",
                    listOf("Sydney", "Melbourne", "Brisbane", "Adelaide"),
                    "Adelaide"
                ),
                QuestionModel(
                    "What is the name of the largest reef system in the world, located off the coast of Queensland?",
                    listOf("Great Barrier Reef", "Coral Sea Reef", "Tasman Barrier Reef", "Sunshine Reef"),
                    "Great Barrier Reef"
                ),
                QuestionModel(
                    "Which Australian state or territory is home to Uluru (Ayers Rock)?",
                    listOf("South Australia", "Western Australia", "Northern Territory", "Queensland"),
                    "Northern Territory"
                ),
                QuestionModel(
                    "What is the name of the desert located in central Australia?",
                    listOf("Simpson Desert", "Gibson Desert", "Great Victoria Desert", "Tanami Desert"),
                    "Simpson Desert"
                ),
                QuestionModel(
                    "Which Australian city hosted the 2000 Summer Olympics?",
                    listOf("Sydney", "Melbourne", "Brisbane", "Perth"),
                    "Sydney"
                ),
                QuestionModel(
                    "What is the name of the large sandstone rock formation in the Northern Territory?",
                    listOf("Wave Rock", "Devils Marbles", "Bungle Bungle Range", "Kata Tjuta"),
                    "Kata Tjuta"
                ),
                QuestionModel(
                    "Which Australian animal is known for its distinctive hopping locomotion?",
                    listOf("Kangaroo", "Koala", "Emu", "Platypus"),
                    "Kangaroo"
                )
            )
        ),
        QuizData(
            "10",
            imgResID = R.drawable.img_asia,
            "Asia - Hard Level",
            "Test your advanced knowledge about Asia",
            "4",
            arrayListOf(
                QuestionModel(
                    "Which mountain is known as the 'Roof of the World'?",
                    listOf("Mount Everest", "K2", "Mount Fuji", "Mount Kilimanjaro"),
                    "Mount Everest"
                ),
                QuestionModel(
                    "Which river is often called the 'Mother River' of China?",
                    listOf("Yangtze River", "Yellow River", "Mekong River", "Ganges River"),
                    "Yellow River"
                ),
                QuestionModel(
                    "What is the largest city in Asia by population?",
                    listOf("Tokyo", "Delhi", "Shanghai", "Mumbai"),
                    "Tokyo"
                ),
                QuestionModel(
                    "Which ancient city was known as 'Babylon'?",
                    listOf("Cairo", "Baghdad", "Damascus", "Jerusalem"),
                    "Baghdad"
                ),
                QuestionModel(
                    "What is the largest desert in Asia?",
                    listOf("Gobi Desert", "Arabian Desert", "Karakum Desert", "Thar Desert"),
                    "Gobi Desert"
                ),
                QuestionModel(
                    "Which country is known as the 'Land of the Rising Sun'?",
                    listOf("China", "South Korea", "Japan", "Vietnam"),
                    "Japan"
                ),
                QuestionModel(
                    "Which Asian country is the largest by land area?",
                    listOf("Russia", "China", "India", "Indonesia"),
                    "Russia"
                ),
                QuestionModel(
                    "What is the tallest building in the world located in Asia?",
                    listOf("Shanghai Tower", "Burj Khalifa", "Taipei 101", "Petronas Towers"),
                    "Burj Khalifa"
                ),
                QuestionModel(
                    "Which island is the most populous in the world and located in Asia?",
                    listOf("Borneo", "Sri Lanka", "Java", "Honshu"),
                    "Java"
                ),
                QuestionModel(
                    "What is the largest religious monument in the world located in Cambodia?",
                    listOf("Angkor Wat", "Taj Mahal", "Borobudur", "Shwedagon Pagoda"),
                    "Angkor Wat"
                )
            )
        ),
    )
}