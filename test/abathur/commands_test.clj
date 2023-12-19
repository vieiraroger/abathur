(ns abathur.commands-test
  (:require [clojure.test :refer :all]
            [abathur.commands :as commands]
            [clojure.string :as s]
            [abathur.path :refer [start-path]]))


; TODO https://github.com/helpshift/faker


(deftest pwd-test
  (testing "pwd after start the system"
    (let [path (:path (start-path))
          pwd (commands/pwd)]
      (println pwd)
      (is (= path
             (:message pwd)))
      (is (:status pwd)))))


(deftest rm-and-mkdir-test
  (let [folder-name "testing2321323"
        file-name "adasdqeqe14123"]
    (testing "creating a folder"
      (is (:status (commands/mkdir folder-name))))
    (testing "trying to create a folder that already exist - was created on last test"
      (is (not (:status (commands/mkdir folder-name)))))
    (testing "creating a file"
      (is (:status (commands/mkdir file-name))))
    (testing "deleting file - was created on last test"
      (is (:status (commands/rm file-name))))
    (testing "deleting folder - was created on last test"
      (is (:status (commands/rm folder-name))))))


; TODO create something that automatics create and delete the folders (use-fiixtures maybe)

(deftest cd-test
  (let [starter-path (:message (commands/pwd))
        folder-name "adasdqqweqwe"
        created (commands/mkdir folder-name)]
    (testing "creating fodler for cd test"
      (is (:status created)))
    (testing "entering cd"
      (is (:status (commands/cd folder-name))))
    (testing "is in the folder"
      (is (s/ends-with? (:message (commands/pwd)) folder-name)))
    (testing "back one folder"
      (is (= starter-path
             (:message (commands/cd "..")))))
    (testing "deleting folder used on cd test"
      (is (:status (commands/rm folder-name))))))