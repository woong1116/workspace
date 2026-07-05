export default function StylePage() {
  return (
    <div className="bg-white rounded-lg p-4 shadow-md">
      {/* // 기본 카드 */}
      <div className="bg-white rounded-lg shadow-md p-6">카드 내용</div>

      {/* // 호버 효과가 있는 카드 */}
      <div className="bg-white rounded-lg shadow-md p-6 hover:shadow-lg transition-shadow">
        카드 내용
      </div>

      {/* // 테두리 있는 카드 */}
      <div className="bg-white rounded-lg border border-gray-200 p-6">
        카드 내용
      </div>

      {/* // Primary 버튼 */}
      <button className="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600 transition-colors">
        Primary
      </button>

      {/* // Secondary 버튼 */}
      <button className="px-4 py-2 bg-gray-200 text-gray-800 rounded-lg hover:bg-gray-300 transition-colors">
        Secondary
      </button>

      {/* // Outline 버튼 */}
      <button className="px-4 py-2 border-2 border-blue-500 text-blue-500 rounded-lg hover:bg-blue-50 transition-colors">
        Outline
      </button>

      {/* // 위험 버튼 */}
      <button className="px-4 py-2 bg-red-500 text-white rounded-lg hover:bg-red-600 transition-colors">
        Delete
      </button>

      {/* // 기본 Input */}
      <input
        className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
        placeholder="입력하세요"
      />

      {/* // 에러 상태 Input */}
      <input
        className="w-full px-3 py-2 border border-red-500 rounded-lg focus:outline-none focus:ring-2 focus:ring-red-500"
        placeholder="에러 상태"
      />
    </div>
  );
}
