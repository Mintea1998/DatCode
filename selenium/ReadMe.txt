// Flow
src
├── main
│	│└── java
│	│    └── com
│	│ 		 └── fnb //Project name
│	│            ├── web
│	│            ├   ├── pages //Prepare data test, test methods
│	│            ├   ├── scenario_tests //Write scenario test scripts
│	│            ├   └── webdriver
│	│            ├       ├── linux //Using webdriver in linux
│	│            ├       └── windown //Using webdriver in windown
│	│            ├
│	│            ├── mobile
│	│            ├	 ├── android 
│	│            ├	 └── iso 
│	│            ├
│	│            └── utils //Shared custom method, function, config
│	test
│   └── java
│       └── com
│		    └── fnb //Project name
│				└── test_ng //Test all scenario tests 
│				    ├── web
│				    └── mobile
│
├── target
│
├── config.json //Config used for the system
│
├── pom.xml //Install libary for the system
│
├── test-output //Show log output
│
└── screenshot //Save evidence