(ns abathur.commands
  (:require [abathur.path :as path]
            [abathur.logic :refer [merge-paths print-ls]]))

(defn pwd [] 
  {:status true :message (path/get-path)})

(defn cd [folder]
  (let [merged-path (merge-paths (path/get-path) folder)
        result (path/set-path merged-path)]
    (if result
      {:status true :message merged-path}
      {:status false :message "Diretorio não existe ou é um arquivo"})))

(defn ls []
  (let [files (path/get-files-from-path (path/get-path))]
    (run! print-ls files)
    {:status true :message "" :files-count (count files)}))

(defn mkdir [file-name]
  (let [created (path/create-file (path/get-path) file-name)]
    (if created
      {:status true :message "Arquivo criado com sucesso"}
      {:status false :message "Arquivo não pode ser criado ou já existe"})))

(defn rm [file-name]
  (let [deleted (path/delete-file (path/get-path) file-name)]
    (if deleted
      {:status true :message "Arquivo criado com deletado"}
      {:status false :message "Arquivo não pode ser deletado"})))

; TODO enum com as mensagens em portugues e ingles utilizar o :xyz