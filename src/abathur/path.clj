(ns abathur.path
  (:require [clojure.java.io :as io]
            [clojure.string :as s]
            [environ.core :refer [env]]))

(def actual-path (atom {:path ""}))

(defn is-path-valid? [path]
  (if (s/includes? path ".")
    false
    (->> path
         (io/file)
         (.exists))))

(defn set-path [path]
  (if (is-path-valid? path)
    (do (swap! actual-path assoc :path path)
        true)
    false))

(defn start-path
  ([]
   (swap! actual-path assoc :path (System/getProperty "user.dir")))
  ([path]
   (swap! actual-path assoc :path path)))

(defn get-path []
  (if (not= (:path @actual-path) "")
    (:path @actual-path)
    (do
      (start-path)
      (:path @actual-path))))

(defn get-files-from-path
  [path]
  (->> path
       (io/file)
       (io/.listFiles)))

(defn create-file
  [path file-name]
  (-> (str path "\\" file-name)
      (io/file)
      (as-> file
            (if (s/includes? file-name ".")
              (io/.createNewFile file)
              (io/.mkdirs file)))))


  ; TODO regular expresison like "delete %roger.txt" or "delete *roger.txt"
(defn delete-file
  [path file-name]
  (-> (str path "/" file-name)
      (io/file)
      (io/.delete)))
