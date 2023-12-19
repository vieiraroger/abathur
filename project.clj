(defproject abathur "abathur"
  :description "Abathur is a custom command line application for windows"
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [environ "1.2.0"]]
  :plugins [[lein-environ "1.2.0"]]
  :main ^:skip-aot abathur.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
