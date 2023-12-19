(ns abathur.logic
  (:require [clojure.string :as s]))


(defn rightbar->left-bar
  [path]
  (-> path
      (s/replace #"//" "/")
      (s/replace  #"/" "\\")))

(defn back-one-folder [folders]
  (drop-last folders))

(defn reducer-path-specials
  [path new]
  (case new
    nil path
    ".." (back-one-folder path)
    (conj path new)))

(defn pathstring->patharray [path]
  (s/split path #"\\"))

(defn patharray->pathstring
  [array]
  (reduce #(str %1 "\\" %2) array))

(defn merge-paths
  [start end]
   (let [merged-path (str (rightbar->left-bar start) "\\"
                          (rightbar->left-bar end))
         folders-children (pathstring->patharray merged-path)]
     (->> (reduce reducer-path-specials [] folders-children)
          patharray->pathstring)))

(defn print-ls [file]
  (if (.isDirectory file)
    (print "d ")
    (print "- "))
  (println (.getName file)))